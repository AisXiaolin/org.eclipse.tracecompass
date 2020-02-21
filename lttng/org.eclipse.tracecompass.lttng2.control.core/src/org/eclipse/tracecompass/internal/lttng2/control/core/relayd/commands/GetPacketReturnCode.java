/**********************************************************************
 * Copyright (c) 2014 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Matthew Khouzam - Initial implementation and API
 *   Marc-Andre Laperle - Initial implementation and API
 **********************************************************************/

package org.eclipse.tracecompass.internal.lttng2.control.core.relayd.commands;

/**
 * Get packet return code
 *
 * @author Matthew Khouzam
 */
public enum GetPacketReturnCode implements IBaseCommand {

    /** Response was OK */
    VIEWER_GET_PACKET_OK(1),
    /** Response was RETRY */
    VIEWER_GET_PACKET_RETRY(2),
    /** Response was ERROR */
    VIEWER_GET_PACKET_ERR(3),
    /** Response was End of File */
    VIEWER_GET_PACKET_EOF(4);

    private final int fCode;

    private GetPacketReturnCode(int c) {
        fCode = c;
    }

    @Override
    public int getCommand() {
        return fCode;
    }

}