/*
* Проанализировать персонажей "Крестьянин, Разбойник, Снайпер, Колдун, Копейщик, Арбалетчик, Монах".
* Для каждого определит 8 полей данных(здоровье, сила итд) 3-4 поля поведения(методов атаковать, вылечить итд).
* Создать абстрактный класс и иерархию наследников. Расположить классы в пакет так,
*  чтобы в основной программе небыло видно их полей. В не абстрактных классах переопределить метод toString() так,
*  чтобы он возвращал название класса или имя.
* Создать в основной программе по одному обьекту каждого не абстрактного класса и вывести в консоль его имя.

Формат сдачи:
Ссылка на гитхаб проект
Подписать фамилию и номер группы
*
* Крестьянин:            Разбойник:           Снайпер:           Колдун:
* Сила                   Ловкость             Ловкость           Мана
* Выносливость           Выносливость         Количество БП      Стихия
* Сопротивляемость       Уклонение            Меткость           Кол-во слов силы
*   Скорость атаки!         Скорость атаки!       Скорость атаки!     Скорость атаки!
*   Оружие!                 Оружие!               Оружие!             Оружие!
*   Броня!                  Броня!                Броня!              Броня!
*   Здоровье!               Здоровье!             Здоровье!           Здоровье!
*   Имя!                    Имя!                  Имя!                Имя!
*
* Копейщик:              Арбалетчик:          Монах:
* Сила                   Ловкость             Мана
* Уклонение              Количество БП        Выносливость
* Защита                 Меткость             Кол-во слов силы
*   Скорость атаки!         Скорость атаки!       Скорость атаки!     Скорость атаки!
*   Оружие!                 Оружие!               Оружие!             Оружие!
*   Броня!                  Броня!                Броня!              Броня!
*   Здоровье!               Здоровье!             Здоровье!           Здоровье!
*   Имя!                    Имя!                  Имя!                Имя!
*
*
* */

package com.mygdx.game.Game;

import com.mygdx.game.Game.AbstractUnit.AbstractUnit;
import com.mygdx.game.Game.Mage.Monk;
import com.mygdx.game.Game.Mage.Witch;
import com.mygdx.game.Game.Shooters.Crossbowman;
import com.mygdx.game.Game.Shooters.Sniper;
import com.mygdx.game.Game.Warriors.Rogue;
import com.mygdx.game.Game.Warriors.Spearman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static ArrayList<AbstractUnit> holyTeam = new ArrayList<>();
    public static ArrayList<AbstractUnit> darkTeam = new ArrayList<>();
    public static ArrayList<AbstractUnit> allTeam = new ArrayList<>();
    public static void main(String[] args) {
        getUnits();

        Scanner scanner = new Scanner(System.in);

        allTeam.addAll(darkTeam);
        allTeam.addAll(holyTeam);
        allTeam.sort((o1, o2) -> o2.getSpeed() - o1.getSpeed());
        System.out.println();
        boolean flag = true;

        Scanner scan = new Scanner(System.in);
        while (true){
            View.view();
            scan.nextLine();
            int summHp1 = 0;
            int summHp2 = 0;
            for (AbstractUnit unit : holyTeam){
                summHp1 += unit.getHp();
            }
            for (AbstractUnit unit : darkTeam) {
                summHp2 += unit.getHp();
            }
            if (summHp1 < 1){
                System.out.println("Победили силы тьмы");
                return;
            }

            else if (summHp2 < 1){
                System.out.println("Победили силы света");
                return;
            }


            for (AbstractUnit unit : allTeam) {
                if(holyTeam.contains(unit)) unit.step(darkTeam, holyTeam);
                        else unit.step(holyTeam , darkTeam);
            }
            /*if (holyTeam.size() == 0){

            }*/
        }
    }

    private static String getName(){
        return String.valueOf(Names.values()[new Random().nextInt(Names.values().length-1)]);
    }

    private static void getUnits(){
        int quantity = 10;
        Random rand = new Random();
        for (int i = 1; i < quantity + 1; i++) {
            int value = rand.nextInt(7);
            switch (value){
                case 0:
                    holyTeam.add(new Peasant(getName(), i, 1));
                    break;
                case 1:
                    holyTeam.add(new Rogue(getName(), i, 1));
                    break;
                case 2:
                    holyTeam.add(new Spearman(getName(), i, 1));
                    break;
                case 3:
                    holyTeam.add(new Sniper(getName(), i, 1, 15));
                    break;
                case 4:
                    holyTeam.add(new Crossbowman(getName(), i, 1, 15));
                    break;
                case 5:
                    holyTeam.add(new Monk(getName(), i, 1, 10));
                    break;
                case 6:
                    holyTeam.add(new Witch(getName(), i, 1, 10));
                    break;
            }
        }
        for (int i = 1; i < quantity + 1; i++) {
            int value = rand.nextInt(7);
            switch (value){
                case 0:
                    darkTeam.add(new Peasant(getName(), i, 10));
                    break;
                case 1:
                    darkTeam.add(new Rogue(getName(), i, 10));
                    break;
                case 2:
                    darkTeam.add(new Spearman(getName(), i, 10));
                    break;
                case 3:
                    darkTeam.add(new Sniper(getName(), i, 10, 15));
                    break;
                case 4:
                    darkTeam.add(new Crossbowman(getName(), i, 10, 15));
                    break;
                case 5:
                    darkTeam.add(new Monk(getName(), i, 10, 10));
                    break;
                case 6:
                    darkTeam.add(new Witch(getName(), i, 10, 10));
                    break;
            }
        }
    }
}
