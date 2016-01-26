# :sparkles: Install Apache Spark :sparkles:

1. Download pre-built version of [Apache Spark](http://spark.apache.org/downloads.html)
2. Unpack the downloaded file and refer to the unpacking folder as *LOCAL_SPARK_HOME*
3. Create a profile file with the following exports
 ```shell
 export SPARK_HOME=LOCAL_SPARK_HOME
 export PYSPARK_PYTHON=$HOME/.pyvenv/IPython/bin/python3
 export PYSPARK_DRIVER_PYTHON=ipython3
 export PYSPARK_DRIVER_PYTHON_OPTS='notebook' pyspark
 
 export PATH=$SPARK_HOME/bin:$PATH
 ```
4. *source* the created profile file
5. Run *pyspark* to start IPython notebook
