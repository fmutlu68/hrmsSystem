package furkan.hrmssystem.core.utilities.results;

public class SuccessResult extends Result{
    public SuccessResult(String message) {
        super(message, true);
    }

    public SuccessResult() {
        super(true);
    }
}
