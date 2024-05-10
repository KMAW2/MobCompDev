package ru.mirea.ivanovea.employeedb;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        Employee hero1 = new Employee();
        hero1.name = "Человек-бетон";
        hero1.skill = "Превращаться в бетон";

        Employee hero2 = new Employee();
        hero2.name = "Человек-кирпич";
        hero2.skill = "Превращаться в кирпич";

        Employee hero3 = new Employee();
        hero3.name = "Человек-студент";
        hero3.skill = "Составлять уникальный диплом из чужих дипломов";

// запись сотрудников в базу
        employeeDao.insert(hero1);
        Log.d(TAG, hero1.name + " умеет " + hero1.skill);
        employeeDao.insert(hero2);
        Log.d(TAG, hero2.name + " умеет " + hero2.skill);
        employeeDao.insert(hero3);
        Log.d(TAG, hero3.name + " умеет " + hero3.skill);

// Загрузка всех работников в список
        List<Employee> employees = employeeDao.getAll();

// Получение определенного работника с id = 1
        Employee new_hero = employeeDao.getById(1);

// Обновление полей объекта
        new_hero.skill = "Превращаться в воск";
        employeeDao.update(hero1);
        Log.d(TAG, "Upd: теперь "+new_hero.name + " умеет " + new_hero.skill);
    }
}