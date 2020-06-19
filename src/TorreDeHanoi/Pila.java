package TorreDeHanoi;
public class Pila {
    private int ContNodo;
    private Nodo Cabeza;

    public Pila() {
        setCabeza(null);
        setContNodo(0);    }
    
    public void setContNodo(int ContNodo) {
        this.ContNodo = ContNodo;
    }

    public void setCabeza(Nodo Cabeza) {
        this.Cabeza = Cabeza;
    }

    public int getContNodo() {
        return ContNodo;
    }

    public Nodo getCabeza() {
        return Cabeza;
    }
    public void Push(Nodo n){
        ContNodo++;
        if(getCabeza()==null){
            setCabeza(n);
        }else{
            n.setAbajo(Cabeza);
            getCabeza().setArriba(n);
            setCabeza(n);
        }
    }
    public void Pop(){
        if(getContNodo()>0){
            ContNodo--;
            setCabeza(Cabeza.getAbajo());
        }
    }
    public String Peek(){
        return Cabeza.getDato();
    }
}
