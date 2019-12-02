package ru.innopolis.stc.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapClass extends Mapper<LongWritable, Text, Text, IntWritable>{
	 
	private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		line = line.replaceAll("[^А-Яа-я]", " ");
		StringTokenizer st = new StringTokenizer(line);

		while (st.hasMoreTokens()) {
			word.set(st.nextToken());
			context.write(word, one);
		}

	}
}
