package com.example.groupfour.service;

import com.example.groupfour.dto.UserExport;
import com.example.groupfour.entity.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ExcelService {
    List<User> readUserFromExcelFile(String excelFilePath) throws IOException;

    void writeUserToExcelFile(ArrayList<UserExport> userExports) throws IOException;
    void InsertUserToDB(List<User> userList);
}
