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
        setupInsertToCategories("NULL,	'Nasi,Mie & Pasta',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Makaroni',	'1',	'',	NULL");
        setupInsertToCategories("NULL,	'keju',	'1',	'',	NULL");
        setupInsertToCategories("NULL,	'Mie',	'1',	'',	NULL");
        setupInsertToCategories("NULL,	'Nasi',	'1',	'',	NULL");
        setupInsertToCategories("NULL,	'Pasta',	'1',	'',	NULL");
//7
        setupInsertToCategories("NULL,	'Roti & Sereal',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Biskuit',	'7',	'',	NULL");
        setupInsertToCategories("NULL,	'Oatmeal',	'7',	'',	NULL");
        setupInsertToCategories("NULL,	'Roti Tawar',	'7',	'',	NULL");
        setupInsertToCategories("NULL,	'Roti Gandum',	'7',	'',	NULL");
        setupInsertToCategories("NULL,	'Roti Panggang',	'7',	'',	NULL");
        setupInsertToCategories("NULL,	'Sereal',	'7',	'',	NULL");
//14
        setupInsertToCategories("NULL,	'Buah',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Alpukat',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Anggur',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Apel',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Jeruk',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Pir',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Kurma',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Mangga',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Melon',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Nanas',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Pepaya',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Pisang',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Semangka',	'14',	'',	NULL");
        setupInsertToCategories("NULL,	'Stroberi',	'14',	'',	NULL");
//28
        setupInsertToCategories("NULL,	'Telur',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Kuning Telur',	'28',	'',	NULL");
        setupInsertToCategories("NULL,	'Putih Telur',	'28',	'',	NULL");
        setupInsertToCategories("NULL,	'Telur Dadar',	'28',	'',	NULL");
        setupInsertToCategories("NULL,	'Telur Goreng',	'28',	'',	NULL");
        setupInsertToCategories("NULL,	'Telur Rebus',	'28',	'',	NULL");
//34
        setupInsertToCategories("NULL,	'Daging',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Ayam',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Babi',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Bebek',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Bakso',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Domba',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Sapi',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Kalkun',	'34',	'',	NULL");
        setupInsertToCategories("NULL,	'Sosis',	'34',	'',	NULL");
//43
        setupInsertToCategories("NULL,	'Ikan Seafood',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Cumi',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Gurita',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Kakap',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Kembung',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Lele',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Salmon',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Sarden',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Teri',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Kepiting',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Lobster',	'43',	'',	NULL");
        setupInsertToCategories("NULL,	'Udang',	'43',	'',	NULL");
//55
        setupInsertToCategories("NULL,	'Sayur',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Acar',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Bayam',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Brokoli',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Jagung',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Jamur',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Kentang',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Kol',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Timun',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Kubis',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Selada',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Terong',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Tomat',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Ubi',	'55',	'',	NULL");
        setupInsertToCategories("NULL,	'Wortel',	'55',	'',	NULL");
//70
        setupInsertToCategories("NULL,	'Minuman',	'0',	'',	NULL");
        setupInsertToCategories("NULL,	'Air Mineral',	'70',	'',	NULL");
        setupInsertToCategories("NULL,	'Kopi',	'70',	'',	NULL");
        setupInsertToCategories("NULL,	'Teh',	'70',	'',	NULL");
        setupInsertToCategories("NULL,	'Jus',	'70',	'',	NULL");
        setupInsertToCategories("NULL,	'Susu',	'70',	'',	NULL");
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
        //Nasi Mie & Pasta
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
        //Roti & Sereal
        setupInsertToFood("NULL,'Roti Tawar','fatsecret','26','gram','1','slice','266','7.64','50.61','3.29','66','1.91','12.65','0.82',NULL,NULL,'10','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Roti Gandum','fatsecret','26','gram','1','slice','259','9.13','47.14','4.11','67','2.37','12.26','1.07',NULL,NULL,'11','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Roti Panggang','fatsecret','26','gram','1','slice','293','9','54.4','4','64','1.98','11.97','0.88',NULL,NULL,'12','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Biskuit','fatsecret','6.5','cm','1','piece','353','7','44.6','16.3','212','4.2','26.76','9.78',NULL,NULL,'8','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Oatmeal','fatsecret','157','gram','1','portion','62','2.5','10.84','1.02','97','4.07','17.02','1.6',NULL,NULL,'9','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Sereal','fatsecret','33','gram','1','portion','376','7.24','83.02','3.38','124','2.39','27.4','1.12',NULL,NULL,'13','roti.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        //Buah
        setupInsertToFood("NULL,'Alpukat','fatsecret','201','gram','1','piece','160','2','8.53','14.66','322','4.02','17.15','29.47',NULL,NULL,'15','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Anggur','fatsecret','1.4','gram','1','piece','69','0.72','18.1','0.16','3','0.04','0.9','0.01',NULL,NULL,'16','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Apel','fatsecret','450','gram','2','pieces','52','0.26','13.81','0.17','110','0.55','29.28','0.36',NULL,NULL,'17','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jeruk','fatsecret','103','gram','1','piece','47','0.94','11.75','0.12','62','1.23','15.39','0.16',NULL,NULL,'18','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pir','fatsecret','450','gram','2','pieces','58','0.38','15.46','0.12','121','0.79','32.31','0.25',NULL,NULL,'19','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kurma','fatsecret','2.8','gram','1','piece','282','2.45','75.03','0.39','23','0.2','6.23','0.03',NULL,NULL,'20','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Mangga','fatsecret','201','gram','1','piece','65','0.51''17','0.27','135','1.06','35.1','0.56',NULL,NULL,'21','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Melon','fatsecret','70','gram','1','slice','34','0.84','8.16','0.19','23','0.58','5.63','0.13',NULL,NULL,'22','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Nanas','fatsecret','57','gram','1','slice','48','0.54','12.63','0.12','27','0.3','7.07','0.07',NULL,NULL,'23','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pepaya','fatsecret','141.75','gram','1','portion','39','0.61','9.81','0.14','55','0.85','13.73','0.2',NULL,NULL,'24','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Pisang','fatsecret','128','gram','1','piece','89','1.09','22.84','0.33','105','1.29','26.95','0.39',NULL,NULL,'25','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Semangka','fatsecret','286','gram','1','slice','30','0.61','7.55','0.15','86','1.74','21.59','0.43',NULL,NULL,'26','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Stroberi','fatsecret','12.4','gram','1','piece','32','0.67','7.68','0.3','4','0.08','0.92','0.04',NULL,NULL,'27','buah.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        //Telur
        setupInsertToFood("NULL,'Kuning Telur','fatsecret','17','gram','1','piece','322','15.86','3.59','26.54','55','2.7','0.61','4.51',NULL,NULL,'28','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Putih Telur','fatsecret','33','gram','1','piece','52','10.9','0.73','0.17','17','3.6','0.24','0.06',NULL,NULL,'29','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Telur Dadar','fatsecret','61.3','gram','1','piece','153','10.62','0.63','12.02','93','6.48','0.42','7.33',NULL,NULL,'30','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Telur Goreng','fatsecret','46','gram','1','piece','194','13.56','0.93','14.69','89','6.24','0.43','6.76',NULL,NULL,'31','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Telur Rebus','fatsecret','50','gram','1','portion','154','12.53','1.12','10.57','77','6.26','0.56','5.28',NULL,NULL,'32','telur.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");

        //Daging
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

        //Ikan & Seafood
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

        //Sayur
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

        //Air
        setupInsertToFood("NULL,'Air Mineral','fatsecret','240''ml','1','cup','0','0','0','0','0','0','0','0',NULL,NULL,'71','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Kopi','fatsecret','180''ml','1','cup coffee','1','0.12','0.04','0.02','2','0.21','0.07','0.04',NULL,NULL,'72','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Teh','fatsecret','240''ml','1','cup','1','0','0.3','0','2','0','0.71','0',NULL,NULL,'73','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jus Apel','fatsecret','300','ml','1','bottle','47','0.06','11.68','0.11','146','0.1','36.21','0.34',NULL,NULL,'74','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Jus Jeruk','fatsecret','300','ml','1','bottle','45','0.7','10.4','0.2','141','2.19','32.7','0.63',NULL,NULL,'74','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
        setupInsertToFood("NULL,'Susu','fatsecret','240''ml','1','cup','60','3.22','4.52','3.25','146','7.86','11.03','7.93',NULL,NULL,'75','air.jpg','foodimage_a.jpg','foodimage_b.jpg','foodimage_c.jpg',NULL");
    }


}
