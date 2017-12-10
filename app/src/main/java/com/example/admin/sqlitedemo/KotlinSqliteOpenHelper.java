package com.example.admin.sqlitedemo;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 9/20/2017.
 */

public class KotlinSqliteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="kotlindb";
    public static final int VERSION=1;

    public static final String TABLE_PERSON="person";
    public static final String COL_ID="id";
    public static final String COL_FIRST_NAME="first_name";
    public static final String COL_LAST_NAME="last_name";
    public static final String COL_MOBILE="mobile";

    public static final String CREATE_PERSON="create table "+TABLE_PERSON+" ("
            +COL_ID+" integer primary key autoincrement, "
            +COL_FIRST_NAME+" TEXT, "+COL_LAST_NAME+" TEXT, "+COL_MOBILE+" TEXT)";

    public static final String TABLE_LINEA_PRODUCCION="linea_produccion";
    public static final String COL_NAME_LP="name";
    public static final String COL_CODIGO_LINEA_PRODUCCION="id_linea_produccion";

    public static final String CREATE_LINEA_PRODUCCION="create table "+TABLE_LINEA_PRODUCCION+" ("
            +COL_CODIGO_LINEA_PRODUCCION+" integer primary key autoincrement, "
            +COL_NAME_LP+" TEXT)";

    public static final String TABLE_PLANTA="planta";
    public static final String COL_NAME_PLANTA="name";
    public static final String COL_CODIGO_PLANTA="id_planta";

    public static final String CREATE_PLANTA="create table "+TABLE_PLANTA+" ("
            +COL_CODIGO_PLANTA+" integer primary key autoincrement, "
            +COL_NAME_PLANTA+" TEXT)";


    public static final String TABLE_MAQUINARIA="maquinaria";
    public static final String COL_CODIGO_MAQ="id_maquinaria";
    public static final String COL_NAME_MAQ="name";


    public static final String CREATE_MAQUINARIA="create table "+TABLE_MAQUINARIA+" ("
            +COL_CODIGO_MAQ+" integer primary key autoincrement, "
            +COL_NAME_MAQ+" TEXT, "
            +COL_CODIGO_LINEA_PRODUCCION+" integer, "
            +COL_CODIGO_PLANTA+" integer,"
            +"FOREIGN KEY("+COL_CODIGO_LINEA_PRODUCCION+") REFERENCES artist("+TABLE_LINEA_PRODUCCION+"),"
            +"FOREIGN KEY("+COL_CODIGO_PLANTA+") REFERENCES artist("+TABLE_PLANTA+") )";

    public KotlinSqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_PERSON);
        sqLiteDatabase.execSQL(CREATE_MAQUINARIA);
        sqLiteDatabase.execSQL(CREATE_LINEA_PRODUCCION);
        sqLiteDatabase.execSQL(CREATE_PLANTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
