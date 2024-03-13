package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game.AbstractUnit.AbstractUnit;
import com.mygdx.game.Game.Mage.Monk;
import com.mygdx.game.Game.Mage.Witch;
import com.mygdx.game.Game.Names;
import com.mygdx.game.Game.Peasant;
import com.mygdx.game.Game.Shooters.Crossbowman;
import com.mygdx.game.Game.Shooters.Sniper;
import com.mygdx.game.Game.Warriors.Rogue;
import com.mygdx.game.Game.Warriors.Spearman;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, crossBowMan, mage, monk, peasant, rouge, sniper, spearMan;

	Music music;

	public ArrayList<AbstractUnit> holyTeam;
	public ArrayList<AbstractUnit> darkTeam;
	public ArrayList<AbstractUnit> allTeam;

	
	@Override
	public void create () {
		holyTeam = new ArrayList<>();
		darkTeam = new ArrayList<>();
		allTeam = new ArrayList<>();

		getUnits();


		allTeam.addAll(darkTeam);
		allTeam.addAll(holyTeam);
		allTeam.sort((o1, o2) -> o2.position.getX() - o1.position.getX());

/*
		allTeam.sort((o1, o2) -> o2.getSpeed() - o1.getSpeed());
*/

		batch = new SpriteBatch();
		fon = new Texture("fon/CmBk" + MathUtils.random(0, 4) + ".png");

		music = Gdx.audio.newMusic(Gdx.files.internal("music/paul-romero-rob-king-combat-theme-0" + MathUtils.random(1, 4) + ".mp3"));
		music.setVolume(.1f);
		music.play();

		this.sniper = new Texture("units/Sniper.png");
		this.crossBowMan = new Texture("units/CrossBowMan.png");
		this.mage = new Texture("units/Mage.png");
		this.rouge = new Texture("units/Rouge.png");
		this.peasant = new Texture("units/Peasant.png");
		this.spearMan = new Texture("units/SpearMan.png");
		this.monk = new Texture("units/Monk.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (AbstractUnit abstractUnit : allTeam) {
			if (abstractUnit.getHp() < 1) continue;

			int y = abstractUnit.position.getX() * Gdx.graphics.getWidth() / 16;
			int x = abstractUnit.position.getY() * Gdx.graphics.getWidth() / 15;
			int k = -1;
			if (holyTeam.contains(abstractUnit)) k = 1;


			switch (abstractUnit.getInfo()){
				case "Монах":
					batch.draw(monk, x, y, monk.getWidth() * k, monk.getHeight());
					break;
				case "Ведьмак":
					batch.draw(mage, x, y, mage.getWidth()* k, mage.getHeight());
					break;
				case "Арбалетчик":
					batch.draw(crossBowMan, x, y, crossBowMan.getWidth()* k, crossBowMan.getHeight());
					break;
				case "Снайпер":
					batch.draw(sniper, x, y, sniper.getWidth()* k, sniper.getHeight());
					break;
				case "Разбойник":
					batch.draw(rouge, x, y, rouge.getWidth()* k, rouge.getHeight());
					break;
				case "Копейщик":
					batch.draw(spearMan, x, y, spearMan.getWidth()* k, spearMan.getHeight());
					break;
				case "Фермер":
					batch.draw(peasant, x, y, peasant.getWidth()* k, peasant.getHeight());
					break;

			}
		}
		batch.end();

		boolean flag = true;
		for(AbstractUnit unit : darkTeam){
			if (unit.getHp() > 0) flag = false;
		}
		if (flag){
			Gdx.graphics.setTitle("Команда сил тьмы победила!");
		}

		flag = true;

		for(AbstractUnit unit : holyTeam){
			if (unit.getHp() > 0) flag = false;
		}
		if (flag){
			Gdx.graphics.setTitle("Команда сил света победила!");
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()){
			for (AbstractUnit unit : allTeam) {
				if(holyTeam.contains(unit)) unit.step(darkTeam, holyTeam);
				else unit.step(holyTeam , darkTeam);
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
		peasant.dispose();
		crossBowMan.dispose();
		sniper.dispose();
		monk.dispose();
		spearMan.dispose();
		rouge.dispose();
		mage.dispose();
		music.dispose();
	}

	private String getName(){
		return String.valueOf(Names.values()[new Random().nextInt(Names.values().length-1)]);
	}

	private void getUnits(){
		int quantity = 10;
		Random rand = new Random();
		for (int i = 1; i < quantity + 1; i++) {
			int value = MathUtils.random(6);
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
			int value = MathUtils.random(6);
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
