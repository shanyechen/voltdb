/* This file is part of VoltDB.
 * Copyright (C) 2008-2013 VoltDB Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
//
// Get Counter Value provided a counter_id
//
package voltcounter.procedures;

import java.util.Date;
import org.voltdb.ProcInfo;
import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;

@ProcInfo(
        partitionInfo = "counter_rollups.rollup_id:0",
        singlePartition = true)
public class InitializeRollup extends VoltProcedure {
    // Inserts a counter

    public final SQLStmt insertStmt = new SQLStmt("INSERT INTO counter_rollups "
            + "(rollup_id, rollup_value, rollup_time) "
            + "VALUES "
            + "(?, ?, ?);");

    public long run(String srollup_id) {

        // get rollup values
        Date ctime = this.getTransactionTime();

        voltQueueSQL(insertStmt, srollup_id, 0, ctime);
        voltExecuteSQL();
        return 0L;
    }
}