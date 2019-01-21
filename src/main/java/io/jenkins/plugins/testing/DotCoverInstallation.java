package io.jenkins.plugins.testing;

import hudson.Extension;
import hudson.init.Initializer;
import hudson.model.Node;
import hudson.model.TaskListener;
import hudson.slaves.NodeSpecific;
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolInstallation;
import hudson.tools.ToolProperty;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static hudson.init.InitMilestone.EXTENSIONS_AUGMENTED;

@SuppressWarnings({"unused", "SpellCheckingInspection"}) // Instantiated by Jenkins
public class DotCoverInstallation extends ToolInstallation implements NodeSpecific<DotCoverInstallation> {

    public static final String DOTCOVERTOOL_DEFAULT_NAME = "Default";
    public static final String WINDOWS_BINARY_NAME = "dotcover.exe";
    private static final long serialVersionUID = 4786571647787802473L;

    /**
     * Default constructor invoked by jenkins.
     *
     * @param name       The name of the installation.
     * @param home       The home directory
     * @param properties tool properties.
     */
    @DataBoundConstructor
    @SuppressWarnings("")
    public DotCoverInstallation(String name, String home, List<? extends ToolProperty<?>> properties) {
        super(name, home, properties);
    }

    /**
     * This method is run every time Jenkins is started. It makes sure there is a least one tool installation, creating a default installation if none exists.
     */
    @Initializer(after = EXTENSIONS_AUGMENTED)
    public static void onLoaded() {
        DescriptorImpl descriptor = (DotCoverInstallation.DescriptorImpl) Jenkins.get().getDescriptor(DotCoverInstallation.class);
        DotCoverInstallation[] installations = descriptor.getInstallations();
        if (installations != null && installations.length > 0) {
            return;
        }

        DotCoverInstallation installation = new DotCoverInstallation(DOTCOVERTOOL_DEFAULT_NAME, "dotcover.exe", Collections.emptyList());
        descriptor.setInstallations(installation);
        descriptor.save();
    }

    public static DotCoverInstallation getDefaultInstallation() {
        DotCoverInstallation defaultInstallation;
        DescriptorImpl descriptor = Jenkins.get().getDescriptorByType(DescriptorImpl.class);
        defaultInstallation = descriptor.findInstallationByName(DOTCOVERTOOL_DEFAULT_NAME);
        if (defaultInstallation != null) {
            return defaultInstallation;
        }

        onLoaded(); // called to make sure there is always a default tool to find afterwards.
        defaultInstallation = descriptor.findInstallationByName(DOTCOVERTOOL_DEFAULT_NAME);
        return defaultInstallation;
    }

    /**
     * Finds the DotCover tool installation for the given node.
     *
     * @param node The node to find the tool installation for.
     * @param log  The instance of the tasklistener to use for logging.
     * @return The DotCover installation associated with the node. Returns the default installation if there are no node-specific matches.
     * @throws IOException          If an IOException occurs.
     * @throws InterruptedException If an InterruptedException occurs.
     */
    @Override
    public DotCoverInstallation forNode(@Nonnull Node node, TaskListener log) throws IOException, InterruptedException {
        final String home = translateFor(node, log);
        return new DotCoverInstallation(getName(), home, Collections.emptyList());
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) Jenkins.get().getDescriptorOrDie(getClass());
    }

    @Extension
    @Symbol("dotcovertool")
    public static class DescriptorImpl extends ToolDescriptor<DotCoverInstallation> {

        public DescriptorImpl() {
            super();
            load();
        }

        @SuppressWarnings("SuspiciousToArrayCall")
        @Override
        public boolean configure(StaplerRequest req, JSONObject json) {
            setInstallations(req.bindJSONToList(clazz, json.get("tool")).toArray(new DotCoverInstallation[0]));
            save();
            return true;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "DotCover";
        }

        public DotCoverInstallation findInstallationByName(String installationName) {
            for (DotCoverInstallation installation : getInstallations()) {
                if (installation.getName().equals(installationName)) {
                    return installation;
                }
            }
            return null;
        }
    }
}
