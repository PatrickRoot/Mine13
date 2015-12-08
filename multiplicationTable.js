/**
 * Created by loki/六楼的雨 on 2015/1/3.
 */

function multiplicationTable( i , j ){
    if( j >= 10 ){
        process.stdout.write( "\n" );
    }else{
        process.stdout.write( i+"*"+j+"="+(i*j) );

        if( i == j ){
            process.stdout.write("\n");
            multiplicationTable( 1, (j+1) );
        }else{
            process.stdout.write("  ");
            multiplicationTable( (i+1) , j );
        }
    }
}

multiplicationTable(1,1);