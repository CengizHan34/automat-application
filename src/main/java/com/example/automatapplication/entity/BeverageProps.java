package com.example.automatapplication.entity;

import com.example.automatapplication.enums.BeverageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author created by cengizhan on 19.06.2021
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BeverageProps {
    @Enumerated(EnumType.STRING)
    private BeverageType beverageType;
}
