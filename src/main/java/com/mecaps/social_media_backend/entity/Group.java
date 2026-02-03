package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.GroupType;
import com.mecaps.social_media_backend.Enum.JoinPolicy;
import com.mecaps.social_media_backend.Enum.PostPolicy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "social_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @Enumerated(EnumType.STRING)
    private JoinPolicy joinPolicy;

    @Enumerated(EnumType.STRING)
    private PostPolicy postPolicy;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<GroupMember> groupMembers = new ArrayList<>();

    public void addMember(GroupMember member){
        groupMembers.add(member);
        member.setGroup(this);
    }

}
