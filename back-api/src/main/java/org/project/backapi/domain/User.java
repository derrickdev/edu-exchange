package org.project.backapi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.project.backapi.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private String fullname;

    @Column(name = "pseudo")
    private String pseudo;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "speciality")
    private String teacherSpeciality;

    @Builder.Default
    private Boolean enabled = Boolean.TRUE;

    // relations
    @OneToMany(mappedBy = "user")
    private Set<Vote> votes;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Post> posts;

    @OneToMany(mappedBy = "initiator")
    @ToString.Exclude
    private Set<Conversation> initiatedConversations;

    @OneToMany(mappedBy = "receiver")
    @ToString.Exclude
    private Set<Conversation> receivedConversations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", userRole=" + userRole +
                ", createdAt=" + createdAt +
                ", teacherSpeciality='" + teacherSpeciality +
                '}';
    }
}
