package com.neutro.geolumenlocalizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.IntroViewPagerAdapter;
import shared.ElementoIntro;
import util.MySharedPreferences;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener{

    private final static String INTROVALUE = "isIntroOpened";
    private MySharedPreferences mySharedPreferences;
    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabLayout;
    private List<ElementoIntro> listaElementi;
    private CardView btn_inizia;
    private CardView btn_next;
    private CardView btn_previous;
    private CardView btn_skip;
    private Animation btn_inizia_anim;
    private int posizione;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Imposta l'intro a full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);
        //Inizializza
        mySharedPreferences = new MySharedPreferences(getApplicationContext());
        //Se l'intro è stata già visionata,passa avanti
        if(mySharedPreferences.recuperaDatoBoolean(INTROVALUE))
        {
            Intent intent = new Intent(IntroActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        viewPager = findViewById(R.id.viewpager_intro);
        tabLayout = findViewById(R.id.tab_intro);
        btn_inizia = findViewById(R.id.btn_iniziamo);
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        btn_skip = findViewById(R.id.btn_skip);
        //Inizializza animazione
        btn_inizia_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animazione_bottone_inizia);
        //Abilita il click su cardView
        btn_next.setOnClickListener(this);
        btn_inizia.setOnClickListener(this);
        btn_skip.setOnClickListener(this);
        btn_previous.setOnClickListener(this);
        //Crea la lista intro
        creaListaIntro();
        //Configura il page adpter
        introViewPagerAdapter = new IntroViewPagerAdapter(this, listaElementi);
        viewPager.setAdapter(introViewPagerAdapter);
        //Configura il tablayout
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if(tab.getPosition() == listaElementi.size()-1)
                {
                    caricaUltimaSchermata();
                }
                else if(tab.getPosition()==1)
                {
                    btn_previous.setVisibility(View.VISIBLE);
                }
                else if (tab.getPosition()==0)
                {
                    btn_previous.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    @Override
    public void onClick(View view)
    {
        Intent intent;
        switch (view.getId())
        {
            case R.id.btn_next:
                posizione = viewPager.getCurrentItem();
                if(posizione < listaElementi.size())
                {
                    posizione++;
                    viewPager.setCurrentItem(posizione);
                }
                if(posizione==listaElementi.size()-1)
                {
                    caricaUltimaSchermata();
                }
                break;
            case R.id.btn_previous:
                posizione = viewPager.getCurrentItem();
                if(posizione>0)
                {
                    posizione--;
                    viewPager.setCurrentItem(posizione);
                }
                break;
            case R.id.btn_iniziamo:
                intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                mySharedPreferences.salvaDatoBoolean(INTROVALUE,true);
                finish();
                break;
            case R.id.btn_skip:
                intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                mySharedPreferences.salvaDatoBoolean(INTROVALUE,true);
                finish();
                break;
        }
    }

    //Crea la lista intro
    private void creaListaIntro()
    {
        listaElementi = new ArrayList<>();
        listaElementi.add(new ElementoIntro(R.string.titolo_slide1,R.string.descrizione_slide1,R.drawable.img_test));
        listaElementi.add(new ElementoIntro(R.string.titolo_slide2,R.string.descrizione_slide2,R.drawable.img_test));
        listaElementi.add(new ElementoIntro(R.string.titolo_slide3,R.string.descrizione_slide3,R.drawable.img_test));
    }

    //Mostra il bottone inizia e nasconde il resto
    private void caricaUltimaSchermata()
    {
        btn_inizia.setVisibility(View.VISIBLE);
        btn_next.setVisibility(View.INVISIBLE);
        btn_skip.setVisibility(View.INVISIBLE);
        btn_previous.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        btn_inizia.setAnimation(btn_inizia_anim);
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
    }
}