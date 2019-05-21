SRC_URI = "git://github.com/CovenantSQL/CovenantSQL.git;protocol=https;branch=develop;tag=v0.6.0"
PV="0.6.0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/src/${GO_IMPORT}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit go

SRC_URI += "\
	file://0001-Resolve-unsupported-FlagSet.Name-in-go1.9.patch;patchdir=${WORKDIR}/${PN}-${PV}/src/${GO_IMPORT} \
	file://0002-Resolve-int-overflow-in-32-bit-platforms.patch;patchdir=${WORKDIR}/${PN}-${PV}/src/${GO_IMPORT} \
"

LDFLAGS = "-pthread"
GO_LINKMODE = "-X main.version=${PV}-${SRCREV} -X github.com/CovenantSQL/CovenantSQL/conf.RoleTag=B"
GO_IMPORT = "github.com/CovenantSQL/CovenantSQL"
GO_INSTALL = "${GO_IMPORT}/cmd/cqld"
CGO_ENABLED = "1"

do_install_append() {
    rm -rf ${D}/usr/lib/go/src/github.com/CovenantSQL/CovenantSQL
}
