package info.reinput.member.domain;

import info.reinput.folder.domain.Folder;
import info.reinput.global.domain.TimeAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "member")
@Entity
public abstract class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private MemberInfo info;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> folders;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Embedded
    private TimeAudit timeAudit;

    protected Member(MemberInfo info, MemberRole role) {
        this.info = info;
        this.role = role;
    }

    public String getName() {
        return info.getName();
    }

    public boolean isEnable() {
        return info.isEnable();
    }
    public abstract String getPassword();

}
