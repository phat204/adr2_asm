package ph37313.poly.asm_adr2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ASM.db";
    private static final int DATABASE_VERSION = 2; // Increment version to reflect changes

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the sig table
        String createSigTable = "CREATE TABLE sig (" +
                "TENDANGKY TEXT PRIMARY KEY, " +
                "USERNAME TEXT, " +
                "PASSWORD TEXT)";
        db.execSQL(createSigTable);

        // Create the HoatDong table
        String createHoatDongTable = "CREATE TABLE HoatDong (" +
                "MAHD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOIDUNG TEXT NOT NULL, " +
                "NGAY TEXT DEFAULT (date('now')))";
        db.execSQL(createHoatDongTable);

        // Insert sample data into HoatDong
        String insertHoatDongData = "INSERT INTO HoatDong (NOIDUNG, NGAY) VALUES " +
                "('Nội dung 1', '2024-07-27'), " +
                "('Nội dung 2', '2024-07-28'), " +
                "('Nội dung 3', '2024-07-29'), " +
                "('Nội dung 4', '2024-07-30'), " +
                "('Nội dung 5', '2024-07-31')";
        db.execSQL(insertHoatDongData);

        // Create the steps table
        String createStepsTable = "CREATE TABLE steps (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "steps INTEGER, " +
                "goal INTEGER)";
        db.execSQL(createStepsTable);

        // Insert sample data into steps
        String insertStepsData = "INSERT INTO steps (date, steps, goal) VALUES " +
                "('2024-08-01', 1200, 1500), " +
                "('2024-08-02', 1500, 1600), " +
                "('2024-08-03', 800, 1000), " +
                "('2024-08-04', 2000, 1800), " +
                "('2024-08-05', 1600, 1600)";
        db.execSQL(insertStepsData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables
        String dropSigTable = "DROP TABLE IF EXISTS sig";
        db.execSQL(dropSigTable);

        String dropHoatDongTable = "DROP TABLE IF EXISTS HoatDong";
        db.execSQL(dropHoatDongTable);

        String dropStepsTable = "DROP TABLE IF EXISTS steps";
        db.execSQL(dropStepsTable);

        // Recreate the tables
        onCreate(db);
    }
}
