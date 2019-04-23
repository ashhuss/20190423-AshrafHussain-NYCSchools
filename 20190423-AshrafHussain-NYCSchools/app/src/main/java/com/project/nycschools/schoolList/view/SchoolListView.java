package com.project.nycschools.schoolList.view;


import com.project.nycschools.schoolList.model.SchoolModel;

import java.util.List;

public interface SchoolListView {

    void showSchoolListData(List<SchoolModel> schoolModelList);

    void onSuccess();

    void onError();

    void checkpoint();
}
