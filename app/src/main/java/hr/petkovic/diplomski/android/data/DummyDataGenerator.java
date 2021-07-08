package hr.petkovic.diplomski.android.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hr.petkovic.diplomski.android.data.model.Entry;
import hr.petkovic.diplomski.android.data.model.EntryType;
import hr.petkovic.diplomski.android.data.model.User;

public class DummyDataGenerator {
    private static Map<String, List<Entry>> entriesMap = new HashMap<>();
    private static List<EntryType> typeList = new ArrayList<>();

    public List<Entry> getEntriesForUsername(String username) {
        if (!hasEntriesForUser(username)) {
            generateEntriesForUsername(username);
        }
        return entriesMap.get(username);

    }

    public List<EntryType> getIncomeTypes() {
        List<EntryType> types = new ArrayList<>();
        if (!doTypesExist()) {
            typeList = generateEntryTypes();
        } else {
            for (EntryType type : typeList) {
                if (type.getMainType().equals("Income")) {
                    types.add(type);
                }
            }
        }
        return types;
    }

    public List<EntryType> getExpenseTypes() {
        List<EntryType> types = new ArrayList<>();
        if (!doTypesExist()) {
            typeList =generateEntryTypes();
        }
        for (EntryType type : typeList) {
            if (type.getMainType().equals("Expense")) {
                types.add(type);
            }
        }

        return types;
    }

    private List<Entry> generateEntriesForUsername(String username) {
        Long id = 1L;
        Random random = new Random();
        List<Entry> returnList = new ArrayList<>();
        if (!hasEntriesForUser(username)) {
            if (!doTypesExist()) {
                generateEntryTypes();
            }
            for (EntryType type : typeList) {
                returnList.add(new Entry(id, random.nextLong(), new Date(), "Random", type, new User(1L, username, username, true)));
            }
        }
        return returnList;
    }

    private boolean hasEntriesForUser(String username) {
        if (entriesMap.containsKey(username)) {
            return true;
        }
        return false;
    }

    private List<EntryType> generateEntryTypes() {
        List<EntryType> types = new ArrayList<>();
        types.add(new EntryType(1L, "Income", "Pay"));
        types.add(new EntryType(2L, "Expense", "Life"));
        types.add(new EntryType(3L, "Expense", "Wife"));
        types.add(new EntryType(4L, "Income", "Side job"));
        return types;
    }

    private boolean doTypesExist() {
        if (typeList.isEmpty())
            return false;
        return true;
    }
}
