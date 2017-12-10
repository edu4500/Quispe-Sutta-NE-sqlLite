package com.example.admin.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 9/20/2017.
 */

public class PlantaDAO {

    KotlinSqliteOpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;

    public PlantaDAO(Context context)
    {
        openHelper=new KotlinSqliteOpenHelper(context);
        sqLiteDatabase=openHelper.getWritableDatabase();
    }

    public String savePlanta(Planta planta)
    {
        ContentValues values=new ContentValues();
        values.put(KotlinSqliteOpenHelper.COL_NAME_PLANTA,planta.getNombre());
        long id=sqLiteDatabase.insert(KotlinSqliteOpenHelper.TABLE_PLANTA,null,values);
        if(id>0)
            return "Success";
        else
            return "Fail";
    }

    public String removePlanta(int id)
    {
        String whereClause=KotlinSqliteOpenHelper.COL_CODIGO_PLANTA + " = ?";
        String[] whereArgs={String.valueOf(id)};
        int count=sqLiteDatabase.delete(KotlinSqliteOpenHelper.TABLE_PLANTA,whereClause,whereArgs);
        if(id>0)
            return "Success";
        else
            return "Fail";
    }
    public String updatePlanta(Planta planta)
    {
        ContentValues values=new ContentValues();
        values.put(KotlinSqliteOpenHelper.COL_NAME_PLANTA,planta.getNombre());
        String whereClause=KotlinSqliteOpenHelper.COL_CODIGO_PLANTA + " = ?";
        String[] whereArgs={String.valueOf(planta.getCodigo())};
        int count=sqLiteDatabase.update(KotlinSqliteOpenHelper.TABLE_PLANTA,values,whereClause,whereArgs);
        if(count>0)
            return "Success";
        else
            return "Fail";
    }
    public ArrayList<Planta> getAllPlanta()
    {
        String[] columns={KotlinSqliteOpenHelper.COL_CODIGO_PLANTA,KotlinSqliteOpenHelper.COL_NAME_PLANTA};
        String selection=null;
        String[] selectionArgs=null;
        String groupBy=null;
        String having=null;
        String orderBy=null;
        Cursor cursor=sqLiteDatabase.query(KotlinSqliteOpenHelper.TABLE_PLANTA,columns,selection,
                selectionArgs,groupBy,having,orderBy);
        ArrayList<Planta> plantas=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                plantas.add(cursorToPlanta(cursor));
            }while (cursor.moveToNext());
        }
        return plantas;
    }

    private Planta cursorToPlanta(Cursor cursor) {
        Planta planta=new Planta();
        planta.setCodigo(cursor.getInt(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_CODIGO_PLANTA)));
        planta.setNombre(cursor.getString(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_NAME_PLANTA)));
        return planta;
    }
}
