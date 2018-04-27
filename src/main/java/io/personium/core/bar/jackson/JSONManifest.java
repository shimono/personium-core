/**
 * personium.io
 * Copyright 2014 FUJITSU LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.personium.core.bar.jackson;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.personium.core.utils.ODataUtils;

/**
 * Mapping class for reading 00_manifest.json.
 */
public class JSONManifest implements IJSONMappedObject {

    /** bar_version. */
    @JsonProperty("bar_version")
    private String barVersion;
    /** box_version. */
    @JsonProperty("box_version")
    private String boxVersion;
    /** DefaultPath. bar_version 1. */
    @JsonProperty("DefaultPath")
    private String oldDefaultPath;
    /** default_path. bar_version 2. */
    @JsonProperty("default_path")
    private String defaultPath;
    /** schema. */
    @JsonProperty("schema")
    private String schema;

    /**
     * Default constructor.
     * If this method does not exist, it will fail with deserialization of Json.
     */
    public JSONManifest() {
    }

    /**
     * Constructor.
     * @param barVersion bar_version
     * @param boxVersion box_version
     * @param defaultPath DefaultPath
     * @param schema schema
     */
    public JSONManifest(String barVersion, String boxVersion, String defaultPath, String schema) {
        this.barVersion = barVersion;
        this.boxVersion = boxVersion;
        this.defaultPath = defaultPath;
        this.schema = schema;
    }

    /**
     * bar_versionプロパティの取得.
     * @return barVersion
     */
    public String getBarVersion() {
        return barVersion;
    }

    /**
     * bar_versionプロパティの設定.
     * @param barVersion barVersion.
     */
    public void setBarVersion(String barVersion) {
        this.barVersion = barVersion;
    }

    /**
     * box_versionプロパティの取得.
     * @return boxVersion
     */
    public String getBoxVersion() {
        return boxVersion;
    }

    /**
     * box_versionプロパティの設定.
     * @param boxVersion boxVersion.
     */
    public void setBoxVersion(String boxVersion) {
        this.boxVersion = boxVersion;
    }

    /**
     * DefaultPathプロパティの取得.
     * @return defaultPath
     */
    public String getOldDefaultPath() {
        return oldDefaultPath;
    }

    /**
     * DefaultPathプロパティの設定.
     * @param oldDefaultPath oldDefaultPath.
     */
    public void setOldDefaultPath(String oldDefaultPath) {
        this.oldDefaultPath = oldDefaultPath;
    }

    /**
     * default_pathプロパティの取得.
     * @return defaultPath
     */
    public String getDefaultPath() {
        return defaultPath;
    }

    /**
     * default_pathプロパティの設定.
     * @param defaultPath defaultPath.
     */
    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    /**
     * schemaプロパティの取得.
     * @return schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * schemaプロパティの設定.
     * @param schema schema.
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * schemaの値チェック.
     * @return true:バリデートOK、false:バリデートNG
     */
    public boolean checkSchema() {
        // schemaの値がnull、またはURL形式でない場合はエラーを返却する。
        // box schemaの末尾/の必須を緩和することを見越してisValidSchemaUriを使わない
        if (this.getSchema() == null
                || !(ODataUtils.isValidUri(this.getSchema()))) {
            return false;
        }
        return true;
    }

    @Override
    @JsonIgnore
    @SuppressWarnings("unchecked")
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        json.put("Name", this.oldDefaultPath);
        json.put("Schema", this.schema);
        return json;
    }
}
