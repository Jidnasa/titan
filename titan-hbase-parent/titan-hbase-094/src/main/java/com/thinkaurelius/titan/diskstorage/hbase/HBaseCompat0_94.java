package com.thinkaurelius.titan.diskstorage.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.io.hfile.Compression;

public class HBaseCompat0_94 implements HBaseCompat {

    @Override
    public void setCompression(HColumnDescriptor cd, String algo) {
        cd.setCompressionType(Compression.Algorithm.valueOf(algo));
    }

    @Override
    public HTableDescriptor newTableDescriptor(String tableName) {
        return new HTableDescriptor(tableName);
    }

    @Override
    public ConnectionMask createConnection(Configuration conf) throws IOException
    {
        return new HConnection0_94(HConnectionManager.createConnection(conf));
    }

    @Override
    public void addColumnFamilyToTableDescriptor(HTableDescriptor tdesc, HColumnDescriptor cdesc)
    {
        tdesc.addFamily(cdesc);
    }

    @Override
    public void setTimestamp(Delete d, long timestamp)
    {
        d.setTimestamp(timestamp);
    }

}