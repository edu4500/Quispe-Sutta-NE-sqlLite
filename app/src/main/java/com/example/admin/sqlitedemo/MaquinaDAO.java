package com.example.admin.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 9/20/2017.
 */

public class MaquinaDAO {

    KotlinSqliteOpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;

    public MaquinaDAO(Context context)
    {
        openHelper=new KotlinSqliteOpenHelper(context);
        sqLiteDatabase=openHelper.getWritableDatabase();
    }

    public String saveMaquina(Maquina maquina)
    {
        ContentValues values=new ContentValues();
        values.put(KotlinSqliteOpenHelper.COL_NAME_MAQ,maquina.getNombre());
        values.put(KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION,maquina.getLíneaDeProduccion());
        values.put(KotlinSqliteOpenHelper.COL_CODIGO_PLANTA,maquina.getPlanta());
        long id=sqLiteDatabase.insert(KotlinSqliteOpenHelper.TABLE_MAQUINARIA,null,values);
        if(id>0)
            return "Success";
        else
            return "Fail";
    }

    public String removeMaquina(int id)
    {
        String whereClause=KotlinSqliteOpenHelper.COL_CODIGO_MAQ + " = ?";
        String[] whereArgs={String.valueOf(id)};
        int count=sqLiteDatabase.delete(KotlinSqliteOpenHelper.TABLE_MAQUINARIA,whereClause,whereArgs);
        if(id>0)
            return "Success";
        else
            return "Fail";
    }
    public String updateMaquina(Maquina maquina)
    {
        ContentValues values=new ContentValues();
        values.put(KotlinSqliteOpenHelper.COL_NAME_MAQ,maquina.getNombre());
        values.put(KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION,maquina.getLíneaDeProduccion());
        values.put(KotlinSqliteOpenHelper.COL_CODIGO_PLANTA,maquina.getPlanta());
        String whereClause=KotlinSqliteOpenHelper.COL_CODIGO_MAQ + " = ?";
        String[] whereArgs={String.valueOf(maquina.getCodigo())};
        int count=sqLiteDatabase.update(KotlinSqliteOpenHelper.TABLE_MAQUINARIA,values,whereClause,whereArgs);
        if(count>0)
            return "Success";
        else
            return "Fail";
    }
    public ArrayList<Maquina> getAllMaquina()
    {
        String[] columns={KotlinSqliteOpenHelper.COL_CODIGO_MAQ,KotlinSqliteOpenHelper.COL_NAME_MAQ,
                    KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION,KotlinSqliteOpenHelper.COL_CODIGO_PLANTA};
        String selection=null;
        String[] selectionArgs=null;
        String groupBy=null;
        String having=null;
        String orderBy=null;
        Cursor cursor=sqLiteDatabase.query(KotlinSqliteOpenHelper.TABLE_MAQUINARIA,columns,selection,
                selectionArgs,groupBy,having,orderBy);
        ArrayList<Maquina> maquinas=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                maquinas.add(cursorToMaquina(cursor));
            }while (cursor.moveToNext());
        }
        return maquinas;
    }

    private Maquina cursorToMaquina(Cursor cursor) {
        Maquina maquina=new Maquina();
        maquina.setCodigo(cursor.getInt(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_CODIGO_MAQ)));
        maquina.setNombre(cursor.getString(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_NAME_MAQ)));
        maquina.setLíneaDeProduccion(cursor.getInt(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION)));
        maquina.setPlanta(cursor.getInt(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_CODIGO_PLANTA)));
        return maquina;
    }
}
