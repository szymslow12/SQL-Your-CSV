package com.codecool.SQLYourCSV.model.data;

import java.util.List;
import java.util.Map;

public interface Data {

    List<String[]> getSingleData(String dataName);

    Map<String, List<String[]>> getAllData();
}
