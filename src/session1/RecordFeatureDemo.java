package session1;
//Java 16 (preview), finalized in Java 17
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RecordFeatureDemo {

    public static void main(String[] args) {

        System.out.println("=== PHASE 1: Normal POJO with 2 properties ===");

        PersonV1 p1 = new PersonV1("Sumanth", LocalDate.of(1993, 5, 10));
        PersonV1 p2 = new PersonV1("Sumanth", LocalDate.of(1993, 5, 10));

        Map<PersonV1, String> map1 = new HashMap<>();
        map1.put(p1, "First Entry");
        map1.put(p2, "Second Entry");

        System.out.println("Map Size (expect 1): " + map1.size());
        System.out.println(map1);
        System.out.println();

        System.out.println("=== PHASE 2: Add new property 'aadhar' but forgot to update equals/hashCode ===");

        PersonV2 q1 = new PersonV2("Sumanth", LocalDate.of(1993, 5, 10), "1111-2222-3333");
        PersonV2 q2 = new PersonV2("Sumanth", LocalDate.of(1993, 5, 10), "9999-8888-7777");

        Map<PersonV2, String> map2 = new HashMap<>();
        map2.put(q1, "First Entry");
        map2.put(q2, "Second Entry"); // Expected to add new entry → BUT DOES NOT

        System.out.println("Map Size (BUG) (expect 2, got 1): " + map2.size());
        System.out.println(map2);
        System.out.println();

        System.out.println("=== PHASE 3: Replace POJO with a Record ===");

        PersonRecord r1 = new PersonRecord("Sumanth", LocalDate.of(1993, 5, 10), "1111-2222-3333");
        PersonRecord r2 = new PersonRecord("Sumanth", LocalDate.of(1993, 5, 10), "9999-8888-7777");

        Map<PersonRecord, String> map3 = new HashMap<>();
        map3.put(r1, "First Entry");
        map3.put(r2, "Second Entry");

        System.out.println("Map Size (Correct): " + map3.size());
        System.out.println(map3);
    }
}

// -------- Phase1: Person with only 2 fields --------
class PersonV1 {
    String name;
    LocalDate birthDate;

    public PersonV1(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        PersonV1 that = (PersonV1) o;
        return name.equals(that.name) && birthDate.equals(that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate);
    }

	@Override
	public String toString() {
		return "PersonV1 [name=" + name + ", birthDate=" + birthDate + "]";
	}
}

// -------- Phase2: Added aadhar but forgot to update equals/hashCode --------
class PersonV2 {
    String name;
    LocalDate birthDate;
    String aadhar; // new field

    public PersonV2(String name, LocalDate birthDate, String aadhar) {
        this.name = name;
        this.birthDate = birthDate;
        this.aadhar = aadhar;
    }

    // equals/hashCode NOT updated for the aadhar field
    @Override
    public boolean equals(Object o) {
        PersonV2 that = (PersonV2) o;
        return name.equals(that.name) && birthDate.equals(that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate);
    }

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + ", birthDate=" + birthDate + ", aadhar=" + aadhar + "]";
	}
}

// -------- Phase3: Record handles everything automatically --------
record PersonRecord(String name, LocalDate birthDate, String aadhar) {}