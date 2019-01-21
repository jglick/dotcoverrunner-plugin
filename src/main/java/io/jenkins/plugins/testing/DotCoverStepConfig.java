package io.jenkins.plugins.testing;

import org.apache.commons.lang.NullArgumentException;

import java.io.Serializable;
import java.util.List;

final class DotCoverStepConfig implements Serializable {

    public static final String DOTCOVER_TEMP_DIR = "dotcover-work";
    public static final String HTML_REPORT_NAME = "index.html";
    public static final String NDEPEND_XML_REPORT_NAME="NDependCoverage.xml";
    public static final String DETAILED_XML_REPORT_NAME="DetailedCoverage.xml";
    public static final String SNAPSHOT_NAME = "snapshot.cov";
    public static final String OUTPUT_DIR_NAME = "DotCover";
    public static final String CONFIG_NAME = "DotCoverConfig.xml";
    public static final String IFRAME_NO_JAVASCRIPT ="<iframe id=\"sourceCode\">";
    public static final String IFRAME_ALLOW_JAVASCRIPT ="<iframe sandbox=\"allow-scripts allow-same-origin allow-top-navigation\" id=\"sourceCode\">";

    private static final long serialVersionUID = 6113092202485062423L;

    private final String vsTestPlatform;
    private final String vsTestCaseFilter;
    private final String vsTestArgs;
    private final String solutionFilePath;
    private final String testAssemblyPaths;
    private final String outputDirectory;
    private final String processInclude;
    private final String coverageInclude;
    private final String coverageClassInclude;
    private final String coverageFunctionInclude;
    private final String coverageAssemblyExclude;
    private final String tempDirectory;
    private final String processExclude;
    private final String vsTestPath;


    DotCoverStepConfig(String solutionFilePath, String tempDirectory, String outputDirectory, String dotCoverSnapshotPath, String vsTestPath, String vsTestPlatform, String vsTestCaseFilter, String vsTestArgs, String testAssemblyPaths, String htmlReportPath, String nDependReportPath, String detailedXmlReportPath, String coverageInclude, String coverageClassInclude, String coverageAssemblyExclude, String processInclude, String processExclude, String coverageFunctionInclude)
    {
        if (dotCoverSnapshotPath == null) throw new NullArgumentException("dotCoverSnapshotPath");
        if (htmlReportPath == null) throw new NullArgumentException("htmlReportPath");
        if (nDependReportPath == null) throw new NullArgumentException("nDependReportPath");
        if (detailedXmlReportPath == null) throw  new NullArgumentException("detailedXmlReportPath");
        if (vsTestPlatform == null) throw  new NullArgumentException("vsTestPlatform");
        if (vsTestCaseFilter == null) throw  new NullArgumentException("vsTestCaseFilter");
        if (testAssemblyPaths == null) throw  new NullArgumentException("testAssemblyPaths");
        this.vsTestPlatform = vsTestPlatform;
        this.vsTestCaseFilter = vsTestCaseFilter;
        this.vsTestArgs = vsTestArgs;
        this.solutionFilePath = solutionFilePath;
        this.outputDirectory = outputDirectory;
        this.processInclude = processInclude;
        this.testAssemblyPaths = testAssemblyPaths;
        this.coverageInclude = coverageInclude;
        this.coverageClassInclude = coverageClassInclude;
        this.coverageFunctionInclude = coverageFunctionInclude;
        this.coverageAssemblyExclude = coverageAssemblyExclude;
        this.tempDirectory = tempDirectory;
        this.processExclude = processExclude;
        this.vsTestPath = vsTestPath;
    }

    public String getVsTestPlatform() {
        return vsTestPlatform;
    }

    public String getVsTestCaseFilter() {
        return  vsTestCaseFilter;
    }

    public String getVsTestArgs() {
        return vsTestArgs;
    }

    public String getSolutionFilePath()
    {
        return solutionFilePath;
    }

    public String getTestAssemblyPath()
    {
        return testAssemblyPaths;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public String getProcessInclude() {
        return processInclude;
    }

    public String getCoverageInclude() {
        return coverageInclude;
    }

    public String getCoverageClassInclude() {
        return coverageClassInclude;
    }

    public String getCoverageFunctionInclude() {
        return coverageFunctionInclude;
    }

    public String getCoverageAssemblyExclude() {
        return coverageAssemblyExclude;
    }

    public String getTempDirectory()
    {
        return tempDirectory;
    }

    public String getProcessExclude() {
        return processExclude;
    }

    public String getVsTestPath() {
        return vsTestPath;
    }
}
