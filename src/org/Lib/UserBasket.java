package org.Lib;

import org.sqldb.HistoryEntity;
import org.sqldb.ShopitemEntity;
import org.sqldb.SqlDb;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserBasket {
    private List<HistoryEntity> historyBasket = null;
    private List<HistoryEntity> purchasedBasket = null;
    double total = 0;
    public UserBasket(int userID){
        LoadBasket(userID);
        LoadPurchasedBasket(userID);
    }

    public ShopitemEntity getShopItem(int itemID){
        EntityManager em = SqlDb.getSqlDb();
        Object obj = em.createQuery("from ShopitemEntity where idItem = " + itemID).getSingleResult();
        return (ShopitemEntity)obj;
    }
    public HistoryEntity[] getHistoryBasket() {
        return historyBasket.toArray(new HistoryEntity[historyBasket.size()]);
    }

    public HistoryEntity[] getPurchasedBasket() {
        return purchasedBasket.toArray(new HistoryEntity[purchasedBasket.size()]);
    }

    public void AddToQuantity(int ItemID){
        EntityManager em = SqlDb.getSqlDb();
        em.getTransaction().begin();
        for (HistoryEntity historyEntity : historyBasket) {
            if(historyEntity.getIditem() == ItemID){
                historyEntity.setQuantity(historyEntity.getQuantity() + 1);
                em.merge(historyEntity);
                em.getTransaction().commit();
                em.close();
                return;
            }
        }
    }

    public void RemoveToQuantity(int ItemID, int UserID){
        EntityManager em = SqlDb.getSqlDb();
        em.getTransaction().begin();
        for (HistoryEntity historyEntity : historyBasket) {
            if(historyEntity.getIditem() == ItemID){
                if(historyEntity.getQuantity() > 1){
                    historyEntity.setQuantity(historyEntity.getQuantity() - 1);
                    em.merge(historyEntity);
                    em.getTransaction().commit();
                    em.close();
                    return;
                }
                else{
                    HistoryEntity removableEntity = (HistoryEntity) em.createQuery("from HistoryEntity where iduser = "+UserID+" and iditem = "+ItemID +" and purchased = " + (byte)0 ).getSingleResult();
                    em.remove(removableEntity);
                    em.getTransaction().commit();
                    em.close();
                    LoadBasket(UserID);
                    return;
                }
            }
        }
    }
    public void addItem(int itemID, int userID){
        HistoryEntity tempEntity = null;
        EntityManager em = SqlDb.getSqlDb();
        em.getTransaction().begin();
        try{
            tempEntity = (HistoryEntity) em.createQuery("from HistoryEntity where iditem = "+itemID+ " and iduser = "+userID +" and purchased = " + (byte)0).getSingleResult();
        }
        catch(Exception e){
        }
        if(tempEntity != null){
            tempEntity.setQuantity(tempEntity.getQuantity() + 1);
            em.merge(tempEntity);
            em.getTransaction().commit();
            em.close();
        }
        else{
            tempEntity = new HistoryEntity();
            tempEntity.setIditem(itemID);
            tempEntity.setIduser(userID);
            tempEntity.setQuantity(1);
            em.merge(tempEntity);
            em.getTransaction().commit();
            em.close();
        }
        LoadBasket(userID);
    }

    public void LoadBasket(int userID){
        EntityManager em = SqlDb.getSqlDb();
        Query query = em.createQuery("from HistoryEntity where iduser = "+ userID +" and purchased = " + (byte)0);
        if(query != null)
            historyBasket = query.getResultList();
        em.close();
    }

    public void LoadPurchasedBasket(int userID){
        EntityManager em = SqlDb.getSqlDb();
        Query query = em.createQuery("from HistoryEntity where iduser = "+ userID +" and purchased = " + (byte)1);
        if(query != null)
            purchasedBasket = query.getResultList();
        em.close();
    }


    public void BuyBasket(int userID){
        EntityManager em = SqlDb.getSqlDb();
        em.getTransaction().begin();
        for (HistoryEntity historyEntity : historyBasket){
            historyEntity.setPrice( getShopItem(historyEntity.getIditem()).getPriceItem() * historyEntity.getQuantity() );
            historyEntity.setPurchased((byte) 1);
            em.merge(historyEntity);
        }
        em.getTransaction().commit();
        em.close();
        historyBasket.clear();
        LoadPurchasedBasket(userID);
    }

}
