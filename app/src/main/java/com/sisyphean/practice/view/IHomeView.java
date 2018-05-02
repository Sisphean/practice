package com.sisyphean.practice.view;

import com.sisyphean.practice.bean.ArticlesBean;

public interface IHomeView extends IView {

    void showList(ArticlesBean articlesBean);

    void loadMore(ArticlesBean articlesBean);

    void refresh();

    void showEmpty();

    void showBanner();

}
