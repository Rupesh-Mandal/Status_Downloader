package com.whatsapp.status.downloader.video.story.saver.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Model.WAnotifiacationtpx;


public class DBtpx extends SQLiteOpenHelper {
 String Tablename,DBname;
 Context context;
    ContentValues values ;
    SQLiteDatabase db ;
    public DBtpx(Context context, String tablename, String DB_name, String chk) {
        super(context, DB_name
                , null, 1);
        if (tablename.contains("+")){
            tablename=tablename.replace("+","");
        }  if (!tablename.contains("name")){
            tablename="name"+tablename;
        }

        Tablename = tablename.trim().replaceAll("\\s+", "");
        DBname = DB_name.trim();
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE "+Tablename.trim()+"(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",Name TEXT" +
                ",Message TEXT" +
                ",Date TEXT" +
                ")");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS "+ Tablename );
  onCreate(db);
    }

   public void adddata(WAnotifiacationtpx item) {

       db = this.getWritableDatabase();
       try {
           onCreate(db);


       }catch (Exception e){
Log.v("x",e.toString());
       }
   values = new ContentValues();
       values.put("Name", item.Name);
       values.put("Message", item.MSG);
       values.put("Date", item.Date);

           if (db.insert(Tablename , null, values) != -1) {
//               Toast.makeText(context, "Whatsapp Message saved !"+Tablename, Toast.LENGTH_SHORT).show();
               Log.v("","Message saved ! "+Tablename);

           }else {

               Log.v("TABS","Message not saved ! "+Tablename );

           }




        db.close();
    }



    public ArrayList<WAnotifiacationtpx> getAll() {
        ArrayList<WAnotifiacationtpx> data = new ArrayList<>();
        data.clear();
        SQLiteDatabase database=getReadableDatabase();
        try {
            Cursor cursor=database.rawQuery("SELECT * FROM "+Tablename ,null);

            int i=0;
            while (cursor.moveToNext())
            {


                data.add(new WAnotifiacationtpx(
                        cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(  cursor.getColumnIndex("Message")),
                        cursor.getString(  cursor.getColumnIndex("Date"))));
                // cursor.getBlob( cursor.getColumnIndex("Image"))));



                i++;
            }

            cursor.close();

        }catch (Exception e){
//            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
        }



        database.close();
        return data;

    }

    public void dellDBaall(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+Tablename);
        db.close();

//        Toast.makeText(context, "Cleared !", Toast.LENGTH_SHORT).show();


    }
    public WAnotifiacationtpx getLast() {

        Log.v("msgx", "inbox  gettting last msg for user inbox from the DBtpx "  );
        WAnotifiacationtpx item = null;
        SQLiteDatabase database=getReadableDatabase();
        try {
            Cursor cursor=database.rawQuery("SELECT * FROM "+Tablename ,null);

            int i=0;


cursor.moveToLast();
            item=new WAnotifiacationtpx(
                        cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(  cursor.getColumnIndex("Message")),
                        cursor.getString(  cursor.getColumnIndex("Date")));





            cursor.close();

        }catch (Exception e){
//            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();

            Log.v("msgx", "DBtpx ERROR"+e  );
        }



        database.close();
        return item;

    }








}

