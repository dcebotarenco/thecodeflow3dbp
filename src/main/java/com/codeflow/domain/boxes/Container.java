package com.codeflow.domain.boxes;

import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.Set;

public class Container extends Box3D {

    private final Set<Orientation> orientations;

    public Container(Long id, Dimensions3D dimensions3D, Set<Orientation> orientations) {
        super(id, dimensions3D);
        this.orientations = orientations;
    }

    public Set<Orientation> getOrientations() {
        return orientations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(orientations, container.orientations)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(orientations)
                .toHashCode();
    }
}
