package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Parent;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Image {

        @Parent
        private Item item;

        @Column(nullable = false)
        private String title;

        private int width;

        private int height;

        public Image(String title, int width, int height) {
                this.title = title;
                this.width = width;
                this.height = height;
        }

        @Override
        public String toString() {
                return "Image{" +
                       "filename='" + title + '\'' +
                       ", width=" + width +
                       ", height=" + height +
                       '}';
        }

        @Override
        public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Image image = (Image) o;
                return width == image.width && height == image.height && Objects.equals(item, image.item) && Objects.equals(title, image.title);
        }

        @Override
        public int hashCode() {
                return Objects.hash(item, title, width, height);
        }
}
