package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.neutro.geolumenlocalizer.R;

import java.util.List;

import shared.ElementoIntro;

public class IntroViewPagerAdapter extends PagerAdapter
{

    private Context context;
    private List<ElementoIntro> listaElementi;

    //Costruttore
    public IntroViewPagerAdapter(Context context, List<ElementoIntro> listaElementi)
    {
        this.context = context;
        this.listaElementi = listaElementi;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layoutScreen =  inflater.inflate(R.layout.layout_intro,null);

        ImageView img = layoutScreen.findViewById(R.id.img_intro);
        TextView titolo = layoutScreen.findViewById(R.id.titolo_intro);
        TextView descrizione = layoutScreen.findViewById(R.id.decrizione_intro);

        titolo.setText(listaElementi.get(position).getTitolo());
        descrizione.setText(listaElementi.get(position).getDescrizione());
        img.setImageResource(listaElementi.get(position).getImg());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    //Prendi la dimensione della lista
    @Override
    public int getCount()
    {
        return listaElementi.size();
    }

    //Restituisci la vista
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    //Distruggi  l'elemento
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }


    
}
