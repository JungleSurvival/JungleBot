package pl.wolny.JungleBot.errorsgen;

import java.util.ArrayList;
import java.util.List;

public class errorTypes {
    public  List<String> errornames = new ArrayList<>();

    public List<String> getErrornames() {
        return errornames;
    }
    public void setErrornames(List<String> list){
        errornames = list;
    }
    public void addErrorName(int id, String error){
        List<String> errornames = getErrornames();
        errornames.add(id, error);
        setErrornames(errornames);
    }
    public void generateAllErrors(){
        addErrorName(0, "Błąd wewnętrzny");
        addErrorName(1, "Taki użytkownik nie istnieje");
        addErrorName(2, "Złe użycie");
        addErrorName(3, "Brak permisji");
        addErrorName(4, "Liczba wiadmości musi być większa niż 1");
        addErrorName(5, "Zła liczba");
        addErrorName(6, "Podano zły czas");
    }

}
