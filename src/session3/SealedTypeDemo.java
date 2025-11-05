package session3;
import java.time.LocalDate;

public class SealedTypeDemo {
	
	// Only these sub types are allowed → no invalid states
	sealed interface LeaveStatus permits Pending, Approved, Rejected, Cancelled {}

	record Pending(LocalDate requestedOn) implements LeaveStatus {}
	record Approved(LocalDate approvedOn, String approver) implements LeaveStatus {}
	record Rejected(String reason) implements LeaveStatus {}
	record Cancelled(LocalDate cancelledOn) implements LeaveStatus {}
	

    public static void main(String[] args) {
        LeaveStatus s1 = new Pending(LocalDate.of(2025, 1, 10));
        LeaveStatus s2 = new Approved(LocalDate.of(2025, 1, 12), "Manager");
        LeaveStatus s3 = new Rejected("Insufficient balance");
        LeaveStatus s4 = new Cancelled(LocalDate.of(2025, 1, 15));

        System.out.println(handle(s1));
        System.out.println(handle(s2));
        System.out.println(handle(s3));
        System.out.println(handle(s4));
    }

    //Switch works WITHOUT default because sealed types guarantee exhaustive cases
    static String handle(LeaveStatus status) {
        return switch (status) {
            case Pending p   -> "Pending since: " + p.requestedOn();
            case Approved a  -> "Approved by: " + a.approver() + " on " + a.approvedOn();
            case Rejected r  -> "Rejected: " + r.reason();
            case Cancelled c -> "Cancelled on: " + c.cancelledOn();
        };
    }
}

