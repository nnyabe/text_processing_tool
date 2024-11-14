package com.example.library_management_system.utils;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.BookModel;
import com.example.library_management_system.modles.MagazineModel;

import java.util.Date;
import java.util.List;

public class TestDb {

    public static void main(String[] args) {

        try{

            MagazineController book = new MagazineController();
            List<MagazineModel> allbooks = book.getAll();
            for ( MagazineModel book_ : allbooks){
                System.out.println(book.toString());
            }
            System.out.println();
            MagazineController magazine = new MagazineController();
            List<MagazineModel> allmagz = magazine.getAll();
            for ( MagazineModel mags : allmagz){
                System.out.println(mags.toString());
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
