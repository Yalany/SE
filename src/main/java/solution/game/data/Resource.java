package solution.game.data;

import com.google.gson.annotations.SerializedName;

public record Resource(@SerializedName("name") String name,
                       @SerializedName("starting_amount") int startingAmount,
                       @SerializedName("minimum_amount") int minimumAmount) {}