package com.example.foodforyou;

import android.content.Context;
import android.database.sqlite.SQLiteException;

public class DBSetupInsert {

    //Variable -------------------------------------------------------------------
    private final Context context;

    // Public Class ------------------------------------------------------------------------
    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }

    // Setup insert to food ----------------------------------------------------------------------



    // Setup insert to categories --------------------------------------------------------------------
    public void setupInsertToCategories(String values){
        try{
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("categories",
                    "_id, category_name, category_parent_id, category_icon, category_note",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert categories.", Toast.LENGTH_SHORT).show();
        }
    }
    public void insertAllCategories(){
        setupInsertToCategories("NULL,'Rice, Mie & Pasta','0','',NULL");
        setupInsertToCategories("NULL,'Makaroni','1','',NULL");
        setupInsertToCategories("NULL,'keju','1','',NULL");
        setupInsertToCategories("NULL,'Mie','1','',NULL");
        setupInsertToCategories("NULL,'Nasi','1','',NULL");
        setupInsertToCategories("NULL,'Pasta','1','',NULL");
//7
        setupInsertToCategories("NULL,'Bread & Cereal','0','',NULL");
        setupInsertToCategories("NULL,'Biskuit','7','',NULL");
        setupInsertToCategories("NULL,'Oatmeal','7','',NULL");
        setupInsertToCategories("NULL,'Roti Gandum','7','',NULL");
        setupInsertToCategories("NULL,'Roti Tawar','7','',NULL");
        setupInsertToCategories("NULL,'Roti Panggang','7','',NULL");
        setupInsertToCategories("NULL,'Sereal','7','',NULL");
//14
        setupInsertToCategories("NULL,'Fruits','0','',NULL");
        setupInsertToCategories("NULL,'Alpukat','14','',NULL");
        setupInsertToCategories("NULL,'Anggur','14','',NULL");
        setupInsertToCategories("NULL,'Apel','14','',NULL");
        setupInsertToCategories("NULL,'Jeruk','14','',NULL");
        setupInsertToCategories("NULL,'Pir','14','',NULL");
        setupInsertToCategories("NULL,'Kurma','14','',NULL");
        setupInsertToCategories("NULL,'Mangga','14','',NULL");
        setupInsertToCategories("NULL,'Melon','14','',NULL");
        setupInsertToCategories("NULL,'Nanas','14','',NULL");
        setupInsertToCategories("NULL,'Pepaya','14','',NULL");
        setupInsertToCategories("NULL,'Pisang','14','',NULL");
        setupInsertToCategories("NULL,'Semangka','14','',NULL");
        setupInsertToCategories("NULL,'Stroberi','14','',NULL");
//28
        setupInsertToCategories("NULL,'Egg','0','',NULL");
        setupInsertToCategories("NULL,'Kuning Telur','28','',NULL");
        setupInsertToCategories("NULL,'Putih Telur','28','',NULL");
        setupInsertToCategories("NULL,'Telur Dadar','28','',NULL");
        setupInsertToCategories("NULL,'Telur Goreng','28','',NULL");
        setupInsertToCategories("NULL,'Telur Rebus','28','',NULL");
//34
        setupInsertToCategories("NULL,'Meat','0','',NULL");
        setupInsertToCategories("NULL,'Ayam','34','',NULL");
        setupInsertToCategories("NULL,'Babi','34','',NULL");
        setupInsertToCategories("NULL,'Bebek','34','',NULL");
        setupInsertToCategories("NULL,'Bakso','34','',NULL");
        setupInsertToCategories("NULL,'Domba','34','',NULL");
        setupInsertToCategories("NULL,'Sapi','34','',NULL");
        setupInsertToCategories("NULL,'Kalkun','34','',NULL");
        setupInsertToCategories("NULL,'Sosis','34','',NULL");
//43
        setupInsertToCategories("NULL,'Fish & Seafood','0','',NULL");
        setupInsertToCategories("NULL,'Cumi','43','',NULL");
        setupInsertToCategories("NULL,'Gurita','43','',NULL");
        setupInsertToCategories("NULL,'Kakap','43','',NULL");
        setupInsertToCategories("NULL,'Kembung','43','',NULL");
        setupInsertToCategories("NULL,'Lele','43','',NULL");
        setupInsertToCategories("NULL,'Salmon','43','',NULL");
        setupInsertToCategories("NULL,'Sarden','43','',NULL");
        setupInsertToCategories("NULL,'Teri','43','',NULL");
        setupInsertToCategories("NULL,'Kepiting','43','',NULL");
        setupInsertToCategories("NULL,'Lobster','43','',NULL");
        setupInsertToCategories("NULL,'Udang','43','',NULL");
//55
        setupInsertToCategories("NULL,'Vegetables','0','',NULL");
        setupInsertToCategories("NULL,'Acar','55','',NULL");
        setupInsertToCategories("NULL,'Bayam','55','',NULL");
        setupInsertToCategories("NULL,'Brokoli','55','',NULL");
        setupInsertToCategories("NULL,'Jagung','55','',NULL");
        setupInsertToCategories("NULL,'Jamur','55','',NULL");
        setupInsertToCategories("NULL,'Kentang','55','',NULL");
        setupInsertToCategories("NULL,'Kol','55','',NULL");
        setupInsertToCategories("NULL,'Timun','55','',NULL");
        setupInsertToCategories("NULL,'Kubis','55','',NULL");
        setupInsertToCategories("NULL,'Selada','55','',NULL");
        setupInsertToCategories("NULL,'Terong','55','',NULL");
        setupInsertToCategories("NULL,'Tomat','55','',NULL");
        setupInsertToCategories("NULL,'Ubi','55','',NULL");
        setupInsertToCategories("NULL,'Wortel','55','',NULL");
//70
        setupInsertToCategories("NULL,'Drinks','0','',NULL");
        setupInsertToCategories("NULL,'Air Mineral','70','',NULL");
        setupInsertToCategories("NULL,'Kopi','70','',NULL");
        setupInsertToCategories("NULL,'Teh','70','',NULL");
        setupInsertToCategories("NULL,'Jus','70','',NULL");
        setupInsertToCategories("NULL,'Susu','70','',NULL");
//76
        setupInsertToCategories("NULL,'Another Food','0','',NULL");
        setupInsertToCategories("NULL,'Brownies','76','',NULL");
        setupInsertToCategories("NULL,'Cokelat','76','',NULL");
        setupInsertToCategories("NULL,'Kue','76','',NULL");
        setupInsertToCategories("NULL,'Donat','76','',NULL");
        setupInsertToCategories("NULL,'Es Krim','76','',NULL");
        setupInsertToCategories("NULL,'Puding','76','',NULL");
        setupInsertToCategories("NULL,'Kerupuk','76','',NULL");
        setupInsertToCategories("NULL,'Keripik Kentang','76','',NULL");
        setupInsertToCategories("NULL,'Popcorn','76','',NULL");
        setupInsertToCategories("NULL,'Sandwich','76','',NULL");

        setupInsertToCategories("NULL,'Package','0','',NULL");
        setupInsertToCategories("NULL,'Nasi Telur Dadar','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Ayam','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Sarden','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Sosis Ayam','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Sosis Sapi','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Lele Goreng','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Udang','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Bebek','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Daging Sapi','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Ayam Terong','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Ayam Kol','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Bayam','87','',NULL");
        setupInsertToCategories("NULL,'Seafood (Udang Kepiting Lobster)','87','',NULL");
        setupInsertToCategories("NULL,'Mie Ayam','87','',NULL");
        setupInsertToCategories("NULL,'Mie Bakso Daging Ayam','87','',NULL");
        setupInsertToCategories("NULL,'Mie Bakso Daging Sapi Timun','87','',NULL");
        setupInsertToCategories("NULL,'Kentang Sosis Daging Ayam','87','',NULL");
        setupInsertToCategories("NULL,'Kentang Sosis Daging Sapi','87','',NULL");
        setupInsertToCategories("NULL,'Oatmeal Pisang','87','',NULL");
        setupInsertToCategories("NULL,'Oatmeal Anggur','87','',NULL");
        setupInsertToCategories("NULL,'Oatmeal Anggur Pisang','87','',NULL");
        setupInsertToCategories("NULL,'Mie Ayam Jumbo','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Ayam Jumbo','87','',NULL");
        setupInsertToCategories("NULL,'Nasi Sarden Jumbo','87','',NULL");
        setupInsertToCategories("NULL,'Plan A','87','',NULL");
        setupInsertToCategories("NULL,'Plan B','87','',NULL");
        setupInsertToCategories("NULL,'Plan C','87','',NULL");
        setupInsertToCategories("NULL,'Plan D','87','',NULL");
        setupInsertToCategories("NULL,'Plan E','87','',NULL");
        setupInsertToCategories("NULL,'Plan F','87','',NULL");
        setupInsertToCategories("NULL,'Plan G','87','',NULL");
        setupInsertToCategories("NULL,'Plan H','87','',NULL");
        setupInsertToCategories("NULL,'Plan I','87','',NULL");
        setupInsertToCategories("NULL,'Plan J','87','',NULL");
    }



    public void setupInsertToFood(String values) {

        try {
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("food",
                    "_id, food_name, food_manufactor_name, food_serving_size_gram, food_serving_size_gram_mesurment, food_serving_size_pcs, food_serving_size_pcs_mesurment, food_energy, food_proteins, food_carbohydrates, food_fat, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calculated, food_fat_calculated, food_user_id, food_barcode, food_category_id, food_thumb, food_image_a, food_image_b, food_image_c, food_notes",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert food.", Toast.LENGTH_SHORT).show();
        }
    }
    //Insert all food into food table
    public void insertAllFood(){
        setupInsertToFood("NULL,'Nasi Putih', 'fatsecret','105','gram','1','portion','129','2.66','27.9','0.28','135','2.79','29.3','0.29',NULL,NULL,'5','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Merah','fatsecret','105','gram','1','portion','110','2.56','22.78','0.89','215','4.99','44.42','0.89',NULL,NULL,'5','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Ketan','fatsecret','116','gram','1','portion','97','2.02','21.09','0.19','113','2.34','24.46','0.22',NULL,NULL,'5','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Goreng','fatsecret','149','gram','1','portion','168','6.3','21.06','6.23','250','9.39','31.38','9.28',NULL,NULL,'5','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Goreng Ayam','fatsecret','149','gram','1','portion','166','6.29','21.12','6.04','747','9.37','31.47','9',NULL,NULL,'5','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Goreng Udang','fatsecret','149','gram','1','portion','162','5.53','21.15','5.86','241','8.24','31.51','8.73',NULL,NULL,'5','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mie','fatsecret','160','gram','1','portion','137','4.51','25.01','2.06','219','7.22','40.02','3.3',NULL,NULL,'4','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mie Telur','fatsecret','160','gram','1','portion','138','4.54','25.16','2.07','221','7.26','40.26','3.31',NULL,NULL,'4','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Bihun','fatsecret','160','gram','1','portion','109','0.91','24.9','0.2','192','1.6','43.82','0.35',NULL,NULL,'4','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sohun','fatsecret','160','gram','1','portion','351','0.16','86.09','0.06','491','0.22','120.53','0.08',NULL,NULL,'4','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Spageti','fatsecret','140','gram','1','portion','157','5.76','30.68','0.92','220','8.06','42.95','1.29',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Lasagna dengan Daging','fatsecret','206','gram','1','portion','163','9.96','17.2','6.01','336','20.52','35.43','12.38',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pasta dengan Saus Daging','fatsecret','255','gram','1','portion','129','7.93','13.07','5.12','329','20.22','33.33','13.06',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Spageti Gandum','fatsecret','140','gram','1','portion','123','53','26.38','0.54','172','7.42','36.93','0.73',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Spageti dengan Saus Tomat dan Bakso','fatsecret','248','gram','1','portion','153','7.22','18.62','5.4','379','17.91','46.18','13.39',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Spageti tanpa Daging dengan Saus Tomat','fatsecret','248','gram','1','portion','118','3.98','21.62','1.61','293','9.87','53.62',3.99',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Spageti Gandum dengan Saus Daging','fatsecret','140','gram','1','portion','141','7.04','17.04','5.26','350','17.46','42.26','0.54',NULL,NULL,'6','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Makaroni','fatsecret','140','gram','1','portion','157','5.76','30.68','0.92','220','8.06','42.95','1.29',NULL,NULL,'2','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Makaroni dengan Keju','fatsecret','243','gram','1','portion','203','7.94','21.46','9.4','493','19.29','52.15','22.84',NULL,NULL,'2','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Makaroni Gandum','fatsecret','140','gram','1','portion','123','5.3','26.38','0.54','172','7.42','36.93','0.76',NULL,NULL,'2','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");


        setupInsertToFood("NULL,'Roti Gandum','fatsecret','26','gram','1','slice','259','9.13','47.14','4.11','67','2.37','12.26','1.07',NULL,NULL,'10','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Roti Tawar','fatsecret','26','gram','1','slice','266','7.64','50.61','3.29','66','1.91','12.65','0.82',NULL,NULL,'11','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Roti Panggang','fatsecret','26','gram','1','slice','293','9','54.4','4','64','1.98','11.97','0.88',NULL,NULL,'12','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Biskuit','fatsecret','6.5','cm','1','piece','353','7','44.6','16.3','212','4.2','26.76','9.78',NULL,NULL,'8','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Oatmeal','fatsecret','157','gram','1','portion','62','2.5','10.84','1.02','97','4.07','17.02','1.6',NULL,NULL,'9','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sereal','fatsecret','33','gram','1','portion','376','7.24','83.02','3.38','124','2.39','27.4','1.12',NULL,NULL,'13','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        setupInsertToFood("NULL,'Alpukat','fatsecret','201','gram','1','piece','160','2','8.53','14.66','322','4.02','17.15','29.47',NULL,NULL,'15','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Anggur','fatsecret','1.4','gram','1','piece','69','0.72','18.1','0.16','3','0.04','0.9','0.01',NULL,NULL,'16','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Apel','fatsecret','450','gram','2','pieces','52','0.26','13.81','0.17','110','0.55','29.28','0.36',NULL,NULL,'17','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jeruk','fatsecret','103','gram','1','piece','47','0.94','11.75','0.12','62','1.23','15.39','0.16',NULL,NULL,'18','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pir','fatsecret','450','gram','2','pieces','58','0.38','15.46','0.12','121','0.79','32.31','0.25',NULL,NULL,'19','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kurma','fatsecret','2.8','gram','1','piece','282','2.45','75.03','0.39','23','0.2','6.23','0.03',NULL,NULL,'20','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mangga','fatsecret','201','gram','1','piece','65','0.51','17','0.27','135','1.06','35.1','0.56',NULL,NULL,'21','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Melon','fatsecret','70','gram','1','slice','34','0.84','8.16','0.19','23','0.58','5.63','0.13',NULL,NULL,'22','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nanas','fatsecret','57','gram','1','slice','48','0.54','12.63','0.12','27','0.3','7.07','0.07',NULL,NULL,'23','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pepaya','fatsecret','141.75','gram','1','portion','39','0.61','9.81','0.14','55','0.85','13.73','0.2',NULL,NULL,'24','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pisang','fatsecret','128','gram','1','piece','89','1.09','22.84','0.33','105','1.29','26.95','0.39',NULL,NULL,'25','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Semangka','fatsecret','286','gram','1','slice','30','0.61','7.55','0.15','86','1.74','21.59','0.43',NULL,NULL,'26','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Stroberi','fatsecret','12.4','gram','1','piece','32','0.67','7.68','0.3','4','0.08','0.92','0.04',NULL,NULL,'27','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");


        setupInsertToFood("NULL,'Kuning Telur','fatsecret','17','gram','1','piece','322','15.86','3.59','26.54','55','2.7','0.61','4.51',NULL,NULL,'29','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Putih Telur','fatsecret','33','gram','1','piece','52','10.9','0.73','0.17','17','3.6','0.24','0.06',NULL,NULL,'30','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Telur Dadar','fatsecret','61.3','gram','1','piece','153','10.62','0.63','12.02','93','6.48','0.42','7.33',NULL,NULL,'31','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Telur Goreng','fatsecret','46','gram','1','piece','194','13.56','0.93','14.69','89','6.24','0.43','6.76',NULL,NULL,'32','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Telur Rebus','fatsecret','50','gram','1','portion','154','12.53','1.12','10.57','77','6.26','0.56','5.28',NULL,NULL,'33','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        setupInsertToFood("NULL,'Ayam','fatsecret','85','gram','1','portion','237','27.07','0','13.49','201','23.01','0','11.47',NULL,NULL,'35','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Dada Ayam','fatsecret','98','gram','1','portion','195','29.55','0','7.72','191','28.96','0','7.57',NULL,NULL,'35','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Paha Ayam','fatsecret','62','gram','1','portion','245','24.85','0','15.36','152','15.41','0','9.85',NULL,NULL,'35','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sayap Ayam','fatsecret','32','gram','1','portion','288','26.64','0','19.3','92','8.52','0','6.18',NULL,NULL,'35','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Babi','fatsecret','85','gram','1','portion','271','27.34','0','17.04''230','23.24','0','14.48',NULL,NULL,'36','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Bebek','fatsecret','138','gram','1','portion','132','18.28','0','5.95','181','25.04','0','8.15',NULL,NULL,'37','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Bakso Daging Sapi','fatsecret','108','gram','1','portion','202','12.41','7.58','13.16','218','13.4','8.18','14.22',NULL,NULL,'38','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Bakso Daging Ayam','fatsecret','108','gram','1','portion','161','19.39','6.94','5.62','174','20.94','7.49','6.07',NULL,NULL,'38','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Domba','fatsecret','109','gram','1','portion','314','22.03','0','24.37','342','24.01','0','26.56',NULL,NULL,'39','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sapi','fatsecret','85','gram','1','portion','288','26.33','0','19.54','245','22.38','0','6.61',NULL,NULL,'40','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kalkun','fatsecret','85','gram','1','portion','187','28.9','0','7.02','159','24.56','0','5.97',NULL,NULL,'41','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sosis Daging Sapi','fatsecret','54','gram','1','portion','325','11.04','3.99','29.08','176','5.96','2.15','15.7',NULL,NULL,'42','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sosis Ayam''fatsecret','57','gram','1','portion','172','17.82','1.52','9.98','98','10.16','0.87','5.69',NULL,NULL,'42','Daging.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");


        setupInsertToFood("NULL,'Cumi','fatsecret','57','gram','1','portion','92','15.58','3.08','1.38','52','8.88','1.76','0.79',NULL,NULL,'44','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Gurita','fatsecret','85.05','gram','1','portion','82','14.91','2.2','1.04','70','12.67','1.87','0.88',NULL,NULL,'45','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kakap','fatsecret','200','gram','1','sliced','100','20.51','0','1.34','218','44.71','0','2.92',NULL,NULL,'46','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kembung','fatsecret','85','gram','1','portion','167','19.32','0','9.36','12','16.42','0','7.96',NULL,NULL,'47','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Lele','fatsecret','85','gram','1','portion','240','17.57','8.54','14.53','204','14.93','7.26','12.35',NULL,NULL,'48','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Salmon','fatsecret','75','gram','1','portion','146','21.62','0','5.93','83','12.32','0','3.38',NULL,NULL,'49','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sarden','fatsecret','85','gram','1','portion','208','24.62','0','11.45','177','20.93','0','9.73',NULL,NULL,'50','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Teri','fatsecret','57','gram','1','portion','210','28.89','0','9.71','94','13','0','4.37',NULL,NULL,'51','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kepiting','fatsecret','85','gram','1','portion','101','20.03','0','1.76','84','17.03','0','1.5',NULL,NULL,'52','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Lobster','fatsecret','85','gram','1','portion','97','20.33','1.27','0.58','82','17.28','1.08','0.49',NULL,NULL,'53','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Udang','fatsecret','85','gram','1','portion','144','27.59','1.24','2.35','122','23.45','1.05','2',NULL,NULL,'54','ikan.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        setupInsertToFood("NULL,'Acar','fatsecret','30','gram','1','portion','18','0.62','4.12','0.19','5','0.19','1.24','0.06',NULL,NULL,'56','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Bayam','fatsecret','285','gram','1','package','23','2.86','3.63','0.39','65','8.12','10.31','1.11',NULL,NULL,'57','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Brokoli','fatsecret','29','gram','1','swekers','34','2.82','6.64','0.37','11','0.87','2.06','0.11',NULL,NULL,'58','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jagung','fatsecret','71','gram','1','hump','86','3.22','19.02','1.18','77','2.9','17.12','1.06',NULL,NULL,'59','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jamur','fatsecret','9','gram','1','piece','22','3.09','3.28','0.34','2','0.31','0.33','0.06',NULL,NULL,'60','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kentang','fatsecret','200','gram','1','piece','70','1.68','15.71','0.1','149','3.58','33.46','0.21',NULL,NULL,'61','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kol','fatsecret','100','gram','1','bowl','25','1.98','5.3','0.1','25','1.98','5.3','0.1',NULL,NULL,'62','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Timun','fatsecret','50','gram','1.5','bowl','15','0.65','3.63','0.11','8','0.34','1.89','0.06',NULL,NULL,'63','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kubis','fatsecret','16.7','gram','1','leaf','24','1.44','5.58','0.12','4','0.22','0.84','0.02',NULL,NULL,'64','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Selada','fatsecret','55','gram','1','portion','14','0.9','2.97','0.14','8','0.5','1.63','0.08',NULL,NULL,'65','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Terong','fatsecret','84','gram','1','bowl','24','1.01','5.7','0.19','20','0.83','4.67','0.16',NULL,NULL,'66','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Tomat','fatsecret','0.6','cm','1','medium slice','18','0.88','3.92','0.2','4','0.18','0.78','0.04',NULL,NULL,'67','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Ubi','fatsecret','144','gram','1','bowl','118','1.53','27.88','0.17','177','2.3','41.82','0.26',NULL,NULL,'68','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Wortel','fatsecret','19','cm','1','piece','41','0.93','9.58','0.24','30','0.67','6.9','0.17',NULL,NULL,'69','sayur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        setupInsertToFood("NULL,'Air Mineral','fatsecret','240','ml','1','cup','0','0','0','0','0','0','0','0',NULL,NULL,'71','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kopi','fatsecret','180','ml','1','cup coffee','1','0.12','0.04','0.02','2','0.21','0.07','0.04',NULL,NULL,'72','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Teh','fatsecret','240','ml','1','cup','1','0','0.3','0','2','0','0.71','0',NULL,NULL,'73','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jus Apel','fatsecret','300','ml','1','bottle','47','0.06','11.68','0.11','146','0.1','36.21','0.34',NULL,NULL,'74','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jus Jeruk','fatsecret','300','ml','1','bottle','45','0.7','10.4','0.2','141','2.19','32.7','0.63',NULL,NULL,'74','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Susu','fatsecret','240','ml','1','cup','60','3.22','4.52','3.25','146','7.86','11.03','7.93',NULL,NULL,'75','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        setupInsertToFood("NULL,'Brownies','fatsecret','34','gram','1','portion','379','4.76','62.54','13.77','129','1.62','21.26','4.68',NULL,NULL,'77','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Cokelat','fatsecret','41','gram','1','bar','505','3.9','59.6','34.2','207','1.6','24.44','14.02',NULL,NULL,'78','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kue','fatsecret','5','gram','1','slice','486','5.42','68.97','21.39','24','0.27','3.4','1.07',NULL,NULL,'79','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kue Keju','fatsecret','75','gram','1','slice','321','5.5','25.5','22.5','257','4.4','20.4','18',NULL,NULL,'79','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kue Bolu','fatsecret','63','gram','1','slice','297','7.3','57.7','4.3','187','4.6','36.35','2.71',NULL,NULL,'79','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Donat','fatsecret','8,5','cm diameters','1','medium piece','421','5','49.7','322.9','198','2.35','23.36','10.76',NULL,NULL,'80','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Donat Coklat','fatsecret','7','cm diameters','1','medium piece','474','5','48','31','204','2.15','20.64','13.33',NULL,NULL,'80','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Es Krim','fatsecret','66','gram','1','cup','201','3.52','24.4','10.72','133','2.32','16.1','7.08',NULL,NULL,'81','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Es Krim Vanilla','fatsecret','71','gram','1','cup','201','3.5','23.6','11','145','2.52','16.99','7.92',NULL,NULL,'81','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Es Krim Stroberi','fatsecret','67','gram','1','cup','192','3.2','27.6','8.4','127','2.11','18.22','5.54',NULL,NULL,'81','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Puding Coklat','fatsecret','112','gram','1','cup','139','2.7','23','4','157','3.05','25.99','4.52',NULL,NULL,'82','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Puding Vanilla','fatsecret','112','gram','1','cup','130','2.3','22','3.6','147','2.6','24.86','4.07',NULL,NULL,'82','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kerupuk','fatsecret','3.9','gram','1','piece','502','7.4','61','25.3','20','0.3','2.44','1.01',NULL,NULL,'83','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Keripik Kentang','fatsecret','28','gram','1','portion','547','6.56','49.74','37.47','153','1.84','13.93','10.49',NULL,NULL,'84','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Popcorn','fatsecret','28','gram','1','portion','387','12.94','77.78','4.54','110','3.67','22.05','1.29',NULL,NULL,'85','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sandwich Keju','fatsecret','83','gram','1','piece','315','10.96','33.25','15.27','261','9.1','27.6','12.67',NULL,NULL,'86','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sandwich Daging Sapi Panggang','fatsecret','136','gram','1','portion','251','19.74','19.13','89.92','341','26.85','26.02','13.49',NULL,NULL,'86','snack.png','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        setupInsertToFood("NULL,'Nasi Telur Dadar','fatsecret','22.03','gram','1','portion','282','13.28','28.53','12.03','228','9.27','29.72','335',NULL,NULL,'88','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Ayam','fatsecret','167','gram','1','portion','374','27.51','27.09','669','287','18.02','29.03','10.14',NULL,NULL,'89','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Sarden','fatsecret','190','gram','1','portion','337','27.28','27.09','509','312','101','29.03','10.02',NULL,NULL,'90','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Sosis Ayam','fatsecret','162','gram','1','portion','301','20.48','29.42','10.26','233','57','30.17','276',NULL,NULL,'91','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Sosis Sapi','fatsecret','159','gram','1','portion','454','13.07','31.89','29.36','311','39','31.45','69',NULL,NULL,'92','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Lele Goreng','fatsecret','190','gram','1','portion','369','20.23','36.44','640','339','76','36.56','544',NULL,NULL,'93','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Udang','fatsecret','190','gram','1','portion','273','30.25','29.14','127','257','26.24','30.35','2.29',NULL,NULL,'94','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Bebek','fatsecret','243','gram','1','portion','261','89,861','27.09','6.23','316','27.83','29.03','8.44',NULL,NULL,'95','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Daging Sapi','fatsecret','190','gram','1','portion','417','28.99','27.09','849','380','25.17','29.03','6.09',NULL,NULL,'96','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Ayam Terong','fatsecret','274','gram','1','portion','390','30.74','33.06','608','356','26.63','33.97','522',NULL,NULL,'97','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Ayam Kol','fatsecret','290','gram','1','portion','391','31.71','33.02','602','361','27.78','34.06','518',NULL,NULL,'98','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Bayam','fatsecret','390','gram','1','portion','152','5.52','31.53','47','200','48','39.61','1.04',NULL,NULL,'99','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Seafood (Udang Kepiting Lobster)','fatsecret','255','gram','1','portion','342','67.95','2.51','215','288','57.76','2.13','19',NULL,NULL,'100','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mie Ayam','fatsecret','192','gram','1','portion','425','31.15.00','25.01','21.36','311','68','40.02.00','9.48',NULL,NULL,'101','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mie Bakso Daging Ayam','fatsecret','268','gram','1','portion','298','23.09','31.95','339','393','28.16.00','47.51.00','9.37',NULL,NULL,'102','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mie Bakso Daging Sapi Timun','fatsecret','318','gram','1','portion','354','17.57','36.22','15.33','445','0.9','50.09.00','17.58',NULL,NULL,'103','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kentang Sosis Daging Ayam','fatsecret','257','gram','1','portion','242','19.05','17.23','10.08','247','59','34.33.00','5.09',NULL,NULL,'104','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kentang Sosis Daging Sapi','fatsecret','254','gram','1','portion','395','0,55','19.07','29.18','325','9.54','35.61','688',NULL,NULL,'105','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Oatmeal Pisang','fatsecret','285','gram','1','portion','151','3.59','33.68','1.35','202','5.36','43.97','110',NULL,NULL,'106','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Oatmeal Anggur','fatsecret','158.04','gram','1','portion','131','3.22','28.94','1.18','100','4.11','772','84',NULL,NULL,'107','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Oatmeal Anggur Pisang','fatsecret','286.04','gram','1','portion','220','4.31','51.78','1.51','205','5.04','44.87','2',NULL,NULL,'108','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mie Ayam Jumbo','fatsecret','384','gram','1','package','850','62.03','50.02','42.72','622','31.48','80.04','817',NULL,NULL,'109','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Ayam Jumbo','fatsecret','334','gram','1','package','748','55.02','55.08','31.28','574','36.04','58.06','20.28',NULL,NULL,'110','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nasi Sarden Jumbo','fatsecret','380','gram','1','package','674','54.56','55.08','23.46','624','47.44','58.06','20.04',NULL,NULL,'111','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan A','Nasi Putih,Paha Ayam,Jeruk,Air Mineral,Bayam','795','gram','1','package','444','31.31','43.28','16.15','414','27.55','55','11.41',NULL,NULL,'112','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan B','Oatmeal, Apel,Susu','847','gram','1','package','174','27,639','29.17','4.44','353','12.48','57.33','437',NULL,NULL,'113','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan C','Nasi Ketan,Mangga,Air Mineral','557','gram','1','package','162','2.53','38.09','0.46','248','3.04','59.56','54',NULL,NULL,'114','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan D','Lasagna,Kepiting,Brokoli,Air Mineral)'560','gram','1','package','298','32.81','1,016,666,667','8.14','431','38.42','37.49','610',NULL,NULL,'115','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan E','Nasi Putih,Ayam,Mangga,Kubis,Teh','647.07','gram','1','package','456','31.68','50.78','14.16','477','27.08','65.95','12.34',NULL,NULL,'116','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan F','Nasi Merah,Bebek Goreng,Acar,Air Mineral','513','gram','1','package','260','21.46','26.09','7.03','401','30.22','45.66','9.01',NULL,NULL,'117','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan G','Nasi Putih,Kepiting,Udang,Acar,Semangka,Teh)','831','gram','1','package','423','51.51','41.11','217','434','45.02','53.89','4.28',NULL,NULL,'118','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan H','Nasi Goreng, Dada Ayam,Kubis,Timun,Pisang,Teh','681.07','gram','1','package','492','39.03','53.41','14.51','560','40.02','61.77','17.32',NULL,NULL,'119','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan I','Sandwich Daging Sapi,Alpukat,Tomat,Air Mineral,Es Krim Stroberi','644.06','gram','1','package','621','25.82','59.18','113.18','794','33.16','62.17','48.54',NULL,NULL,'120','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Plan J','Spageti dengan Saus Daging,Udang,Semangka,Jus Jeruk,Kol,Acar,Kue','1054','gram','1','package','901','44.14','116.02','29.78','782','47.73','111.46','756',NULL,NULL,'121','thumb.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
    }


}
