package com.atlassian.plugins.confluence.markdown.configuration;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.fasterxml.jackson.databind.ObjectMapper;

@Scanned
public class PluginAdminSaveConfigurationAction extends ConfluenceActionSupport {
    public static final String PLUGIN_CONFIG_KEY = "markdown-plugin-config-00";

    private BandanaManager bandanaManager;
    private ConfluenceBandanaContext context = new ConfluenceBandanaContext("markdown-plugin");
    private ObjectMapper objectMapper = new ObjectMapper();

    private MacroConfigModel model;

    @Override
    public String execute() throws Exception {
        bandanaManager.setValue(context, PLUGIN_CONFIG_KEY, objectMapper.writeValueAsString(model));
        return SUCCESS;
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }

    public void setData(String data) throws Exception {
        model = objectMapper.readValue(data, MacroConfigModel.class);
    }
}
