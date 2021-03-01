package org.smartregister.configurableviews.model;

import org.smartregister.view.contract.IBaseConfiguration;
import org.smartregister.view.contract.IView;
import org.smartregister.view.contract.IViewConfiguration;

import java.util.List;
import java.util.Map;

/**
 * Created by ndegwamartin on 13/10/2017.
 */

public class ViewConfiguration implements IViewConfiguration {

    private String identifier;

    private IBaseConfiguration metadata;

    private List<IView> views;

    private Map<String, String> labels;

    private Map jsonView;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public IBaseConfiguration getMetadata() {
        return metadata;
    }

    public void setMetadata(IBaseConfiguration metadata) {
        this.metadata = metadata;
    }

    public List<IView> getViews() {
        return views;
    }

    public void setViews(List<IView> views) {
        this.views = views;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Map getJsonView() {
        return jsonView;
    }

    public void setJsonView(Map jsonView) {
        this.jsonView = jsonView;
    }


}
