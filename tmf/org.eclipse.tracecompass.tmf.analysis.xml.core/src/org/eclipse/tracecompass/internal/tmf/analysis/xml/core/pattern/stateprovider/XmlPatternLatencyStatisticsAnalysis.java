/*******************************************************************************
 * Copyright (c) 2016 École Polytechnique de Montréal
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.tracecompass.internal.tmf.analysis.xml.core.pattern.stateprovider;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.analysis.timing.core.segmentstore.ISegmentStoreProvider;
import org.eclipse.tracecompass.analysis.timing.core.segmentstore.statistics.AbstractSegmentStatisticsAnalysis;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.core.segment.TmfXmlPatternSegment;
import org.eclipse.tracecompass.segmentstore.core.ISegment;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceUtils;

/**
 * Statistic analysis for segments generated by a pattern analysis
 *
 * @author Jean-Christian Kouame
 */
public class XmlPatternLatencyStatisticsAnalysis extends AbstractSegmentStatisticsAnalysis {

    private final @Nullable String fPatternAnalysisId;

    /**
     * Constructor
     *
     * @param patternAnalysisId
     *            The pattern analysis ID
     */
    public XmlPatternLatencyStatisticsAnalysis(@Nullable String patternAnalysisId) {
        fPatternAnalysisId = patternAnalysisId;
    }

    @Override
    protected @Nullable String getSegmentType(@NonNull ISegment segment) {
        if (segment instanceof TmfXmlPatternSegment) {
            TmfXmlPatternSegment patternSegment = (TmfXmlPatternSegment) segment;
            return patternSegment.getName();
        }
        return null;
    }

    @Override
    protected @Nullable ISegmentStoreProvider getSegmentProviderAnalysis(@NonNull ITmfTrace trace) {
        return fPatternAnalysisId != null ? TmfTraceUtils.getAnalysisModuleOfClass(trace, XmlPatternAnalysis.class, fPatternAnalysisId) : null;
    }
}
