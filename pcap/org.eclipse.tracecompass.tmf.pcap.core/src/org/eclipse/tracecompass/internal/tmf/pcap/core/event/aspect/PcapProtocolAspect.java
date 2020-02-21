/*******************************************************************************
 * Copyright (c) 2014, 2015 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Perot - Initial API and implementation
 *   Alexandre Montplaisir - Update to new ITmfEventAspect API
 *   Patrick Tasse - Make pcap aspects singletons
 *******************************************************************************/

package org.eclipse.tracecompass.internal.tmf.pcap.core.event.aspect;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.internal.tmf.pcap.core.event.PcapEvent;
import org.eclipse.tracecompass.internal.tmf.pcap.core.protocol.TmfPcapProtocol;
import org.eclipse.tracecompass.tmf.core.event.ITmfEvent;
import org.eclipse.tracecompass.tmf.core.event.aspect.ITmfEventAspect;

/**
 * The "packet protocol" aspect for pcap events
 *
 * @author Alexandre Montplaisir
 */
public final class PcapProtocolAspect implements ITmfEventAspect<String> {

    /** Singleton instance */
    public static final PcapProtocolAspect INSTANCE = new PcapProtocolAspect();

    private PcapProtocolAspect() {
    }

    @Override
    public String getName() {
        return Messages.getMessage(Messages.PcapAspectName_Protocol);
    }

    @Override
    public String getHelpText() {
        return EMPTY_STRING;
    }

    @Override
    public @Nullable String resolve(ITmfEvent event) {
        if (!(event instanceof PcapEvent)) {
            return null;
        }
        PcapEvent pcapEvent = (PcapEvent) event;
        TmfPcapProtocol protocol = pcapEvent.getMostEncapsulatedProtocol();
        return protocol.getShortName().toUpperCase();
    }
}
