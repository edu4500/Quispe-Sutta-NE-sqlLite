package com.example.admin.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 9/20/2017.
 */

public class LineaProduccionDAO {

    KotlinSqliteOpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;

    public LineaProduccionDAO(Context context)
    {
        openHelper=new KotlinSqliteOpenHelper(context);
        sqLiteDatabase=openHelper.getWritableDatabase();
    }

    public String saveLineaDeProduccion(LineaDeProduccion lineaDeProduccion)
    {
        ContentValues values=new ContentValues();
        values.put(KotlinSqliteOpenHelper.COL_NAME_LP,lineaDeProduccion.getNombre());
        long id=sqLiteDatabase.insert(KotlinSqliteOpenHelper.TABLE_LINEA_PRODUCCION,null,values);
        if(id>0)
            return "Success";
        else
            return "Fail";
    }

    public String removeLineaDeProduccion(int id)
    {
        String whereClause=KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION + " = ?";
        String[] whereArgs={String.valueOf(id)};
        int count=sqLiteDatabase.delete(KotlinSqliteOpenHelper.TABLE_LINEA_PRODUCCION,whereClause,whereArgs);
        if(id>0)
            return "Success";
        else
            return "Fail";
    }
    public String updateLineaDeProduccion(LineaDeProduccion lineaDeProduccion)
    {
        ContentValues values=new ContentValues();
        values.put(KotlinSqliteOpenHelper.COL_NAME_LP,lineaDeProduccion.getNombre());
        String whereClause=KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION + " = ?";
        String[] whereArgs={String.valueOf(lineaDeProduccion.getCodigo())};
        int count=sqLiteDatabase.update(KotlinSqliteOpenHelper.TABLE_LINEA_PRODUCCION,values,whereClause,whereArgs);
        if(count>0)
            return "Success";
        else
            return "Fail";
    }
    public ArrayList<LineaDeProduccion> getAllLineaDeProduccion()
    {
        String[] columns={KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION,KotlinSqliteOpenHelper.COL_NAME_LP};
        String selection=null;
        String[] selectionArgs=null;
        String groupBy=null;
        String having=null;
        String orderBy=null;
        Cursor cursor=sqLiteDatabase.query(KotlinSqliteOpenHelper.TABLE_LINEA_PRODUCCION,columns,selection,
                selectionArgs,groupBy,having,orderBy);
        ArrayList<LineaDeProduccion> lineaDeProduccions=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                lineaDeProduccions.add(cursorToLineaDeProduccion(cursor));
            }while (cursor.moveToNext());
        }
        return lineaDeProduccions;
    }

    private LineaDeProduccion cursorToLineaDeProduccion(Cursor cursor) {
        LineaDeProduccion lineaDeProduccion=new LineaDeProduccion();
        lineaDeProduccion.setCodigo(cursor.getInt(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_CODIGO_LINEA_PRODUCCION)));
        lineaDeProduccion.setNombre(cursor.getString(cursor.getColumnIndex(KotlinSqliteOpenHelper.COL_NAME_LP)));
        return lineaDeProduccion;
    }
}
