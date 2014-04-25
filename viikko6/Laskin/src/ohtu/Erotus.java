
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author Santeri
 */
class Erotus implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinenLuku;
    
    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        edellinenLuku = sovellus.tulos();
        sovellus.miinus(Integer.parseInt(syotekentta.getText()));
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");
    }

    @Override
    public void peru() {
        tuloskentta.setText("" + edellinenLuku);
        sovellus.setTulos(edellinenLuku);
    }

}
