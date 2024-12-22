package users.employees;

import java.util.List;

public class TechSupportSpecialist extends Employee {
    public TechSupportSpecialist(String id, String fullname, String email, String password, String techSupport) {
    }

    public TechSupportSpecialist() {
    }

    public List<TechSupportOrder> getOrders() {
        return null;
    }

    public void acceptOrder(TechSupportOrder order) {
        order.setAccepted(true);
    }

    void rejectOrder(TechSupportOrder order) {
        order.setAccepted(false);
    }
}
