package com.revature.notifications;

        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.revature.groups.Group;
        import com.revature.notifications.notificationType.NotificationType;
        import com.revature.search.Searchable;
        import com.revature.users.User;
        import com.revature.users.usersettings.UserSettings;
        import lombok.*;
        import org.hibernate.annotations.Type;
        import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

        import javax.persistence.*;
        import java.time.LocalDateTime;
        import java.util.List;
        import java.util.Objects;
        import java.util.UUID;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(name = "notification_id", unique = true)
//    @GeneratedValue()
//    @Type(type = "uuid-char")
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id_fk", referencedColumnName = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private NotificationType type_id;

    @Column(name = "creation_date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "other_user_id_fk", referencedColumnName = "user_id")//every foreign key should have JoinColumn
    private User otherUser;

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", type_id=" + type_id +
                ", date=" + date +
                ", otherUser=" + otherUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) && Objects.equals(owner, that.owner) && Objects.equals(type_id, that.type_id) && Objects.equals(date, that.date) && Objects.equals(otherUser, that.otherUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, type_id, date, otherUser);
    }
}