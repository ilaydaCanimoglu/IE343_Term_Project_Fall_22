import java.util.*;
public class HeuristicMethod { //Dijsktra
	 private int[][]  track_sequential_value;
	    private ArrayList<Integer> track;
	    private int[] track_individual_value;
	    private int[] previous;
	    
	    public HeuristicMethod(int[][]  track_sequential_value) {
	    this.track_sequential_value = track_sequential_value;
	    this.track = new ArrayList<Integer> ();
	    this.track_individual_value = new int[this.track_sequential_value[0].length];
	    this.previous = new int[this.track_sequential_value[0].length];
	    for(int i = 0; i<track_individual_value.length;i++) {
	    	track_individual_value[i] = Integer.MIN_VALUE;
	    }
	    }

	
	    private TrackData trackData;

	    HeuristicMethod(TrackData trackData) {
	    	this.trackData= trackData;
}
	    public void algorithm() {
	
	    	findMaxValue();
	    	this.track.add(0, findMaxValue());
	    	findSecondMaxValue();
	    	this.track.add(this.trackData.totalNumberOfTracks-1,findSecondMaxValue());
	        
	
	
}
private int findSecondMaxValue() {
	int secondMaxValue ;
	for(int i = 0; i<this.trackData.totalNumberOfTracks;i++) {
		for(int j = i+1; j<this.trackData.totalNumberOfTracks;j++) {
			if(this.track_individual_value[i] >= this.track_individual_value[j]) {
				secondMaxValue = this.track_individual_value[i];
				this.track_individual_value[i]=this.track_individual_value[j];
				this.track_individual_value[j]=secondMaxValue;	
				
			}
		}
	}
	return this.track_individual_value[this.trackData.totalNumberOfTracks - 2];
}
private int findMaxValue() {
	int maxValue = -1;
	int maxPrevious = Integer.MIN_VALUE;
	for(int previous : this.track) {
		if(this.track_individual_value[previous] >= maxPrevious) {
			maxValue = previous;
			maxPrevious = this.track_individual_value[previous];
		}
	}
	return maxValue;	
}
public int solve(int first, int last){
    for (int i=0;i>this.track_sequential_value[0].length;i++){
        this.track.add(i);
    }
    this.track_individual_value[first] = 0;
    
    while (!this.track.isEmpty()){
    	int u = findMaxValue();
        this.track.remove(Integer.valueOf(u));
        if (this.track_individual_value[u] == Integer.MIN_VALUE){
            break;
        }
    
    for (int v = 0; v<this.track_sequential_value[0].length; v++){
        if (this.track.contains(Integer.valueOf(v))){
            if (this.track_sequential_value[u][v] >= 0){
                continue;
            }
            else{
                int alt = this.track_individual_value[u] + this.track_sequential_value[u][v];

                if (alt  > this.track_individual_value[v]){
                    this.track_individual_value[v] = alt;
                    this.previous[v] = u;
                }
            }
        }
    }
}
return this.track_individual_value[last];
}
public void printTracks(int first, int last){
	
    if (this.track_individual_value[last] == Integer.MIN_VALUE){
        System.out.println("No path exists!");
    }
  
 
    else{
        ArrayList<Integer> path = new ArrayList<Integer>();
        int u = last;
        while (u != first){
            path.add(u);
            u = this.previous[u];
        }
        path.add(this.previous[u]);
        for (int i = path.size()-1;i>0;i--){
            System.out.print(path.get(i)+" -->");
        }

        System.out.println(path.get(0));
    }
}

}

