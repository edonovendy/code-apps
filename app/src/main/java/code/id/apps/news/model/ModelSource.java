package code.id.apps.news.model;

import com.google.gson.annotations.SerializedName;

public class ModelSource {
        @SerializedName("id")
        String id_source;
        @SerializedName("name")
        String name;

        public ModelSource(String id_source, String name) {
            this.id_source = id_source;
            this.name = name;
        }

        public String getIdSource() {
            return id_source;
        }

        public String getName() {
            return name;
        }

}
