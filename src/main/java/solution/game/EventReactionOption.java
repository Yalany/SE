package solution.game;

import com.google.gson.annotations.SerializedName;

public record EventReactionOption(@SerializedName("button_text") String buttonText,
                                  @SerializedName("activation_tokens") String[] activationTokens,
                                  @SerializedName("option_results") GameStateModification[] optionResults) {}
