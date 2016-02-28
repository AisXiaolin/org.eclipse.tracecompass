/*******************************************************************************
 * Copyright (c) 2013, 2014 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Francois Chouinard - Initial API and implementation
 *   Patrick Tasse - Modified from TmfSimpleTimestamp to use nanosecond scale
 *******************************************************************************/

package org.eclipse.tracecompass.tmf.core.timestamp;

/**
 * A simplified timestamp where scale is nanoseconds and precision is set to 0.
 */
public final class TmfNanoTimestamp extends TmfTimestamp {

    private final long fValue;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Default constructor (value = 0)
     */
    public TmfNanoTimestamp() {
        this(0);
    }

    /**
     * Full constructor
     *
     * @param value
     *            the timestamp value
     */
    public TmfNanoTimestamp(final long value) {
        fValue = value;
    }

    @Override
    public long getValue() {
        return fValue;
    }


    @Override
    public int getScale() {
        return ITmfTimestamp.NANOSECOND_SCALE;
    }

    // ------------------------------------------------------------------------
    // ITmfTimestamp
    // ------------------------------------------------------------------------

    @Override
    public ITmfTimestamp normalize(final long offset, final int scale) {
        if (scale == ITmfTimestamp.NANOSECOND_SCALE) {
            return new TmfNanoTimestamp(saturatedAdd(getValue(), offset));
        }
        return super.normalize(offset, scale);
    }

    @Override
    public int compareTo(final ITmfTimestamp ts) {
        if (ts instanceof TmfNanoTimestamp) {
            final long delta = getValue() - ts.getValue();
            return (delta == 0) ? 0 : (delta > 0) ? 1 : -1;
        }
        return super.compareTo(ts);
    }

    @Override
    public ITmfTimestamp getDelta(final ITmfTimestamp ts) {
        if (ts instanceof TmfNanoTimestamp) {
            return new TmfTimestampDelta(getValue() - ts.getValue(), ITmfTimestamp.NANOSECOND_SCALE);
        }
        return super.getDelta(ts);
    }

    /**
     * @since 2.0
     */
    @Override
    public long toNanos() {
        return getValue();
    }

    // ------------------------------------------------------------------------
    // Object
    // ------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof TmfNanoTimestamp)) {
            return super.equals(other);
        }
        final TmfNanoTimestamp ts = (TmfNanoTimestamp) other;

        return compareTo(ts) == 0;
    }

}
