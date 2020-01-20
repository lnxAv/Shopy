package org.Lib;

import org.sqldb.ShopitemEntity;
import org.sqldb.SqlDb;

import javax.persistence.Query;
import java.util.List;

public class PageItems {
    private List<ShopitemEntity> itemList = null;
    public int maxResult = 5;
    private int maxPages = 1;
    public int page = 1;

    public PageItems(){
        SetMaxPages();
        itemList = SetPageItems(1);
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void SetMaxPages() {
        Query query = SqlDb.getSqlDb().createQuery("from ShopitemEntity ");
        maxPages = query.getResultList().size() / maxResult;
    }

    public ShopitemEntity[] getItemList() {
        return itemList.toArray(new ShopitemEntity[itemList.size()]);
    }

    public List<ShopitemEntity> SetPageItems(int _page){
        if(_page <= maxPages){
            page = _page;
        }
        else if(_page <= 0) {
            page = 1;
        }

        Query query = SqlDb.getSqlDb().createQuery("from ShopitemEntity ").setFirstResult((maxResult*page) - maxResult).setMaxResults(maxResult);
        itemList = query.getResultList();
        return itemList;
    }

}
