package shared;

public class ElementoIntro
{
    int titolo, descrizione, img;

    public ElementoIntro(int titolo, int descrizione, int img)
    {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.img = img;
    }

    public void setTitolo(int titolo)
    {
        this.titolo = titolo;
    }

    public void setDescrizione(int descrizione)
    {
        this.descrizione = descrizione;
    }

    public void setImg(int img)
    {
        this.img = img;
    }

    public int getTitolo()
    {
        return titolo;
    }

    public int getDescrizione()
    {
        return descrizione;
    }

    public int getImg()
    {
        return img;
    }
}
