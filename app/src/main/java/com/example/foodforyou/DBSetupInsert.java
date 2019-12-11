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
                    "_id, food_name, food_manufactor_name, food_serving_size, food_serving_mesurment, food_serving_name_number, food_serving_name_word, food_energy, food_proteins, food_carbohydrates, food_fat, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calculated, food_fat_calculated, food_user_id, food_barcode, food_category_id, food_thumb, food_image_a, food_image_b, food_image_c, food_notes",
                    values);
            db.close();
        } catch (SQLiteException e) {
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

    }


}
